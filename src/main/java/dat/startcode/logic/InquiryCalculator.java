package dat.startcode.logic;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.Materials;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;

import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.persistence.Facade;
import java.util.ArrayList;
import java.util.List;

public class InquiryCalculator {
    /* 100 cm udhæng foran + maks afstand på 325 cm mellem stolper + 30 cm udhæng bagtil
     Derfor følgende breakpoint */
    //STOLPER
    private final int COLUMNBREAKPOINT = 4550;
    //SPÆR
    private final int MAXDISTANCERAFTERS = 600;
    private final int RAFTERSTHICKNESS = 45;
    //UNDERSTERNBRÆDDER
    private final int FASCIABOARDTHICKNESS = 25;
    //TAG
    private final int FLATROOFBIGBREAKPOINT = 6000 - 150;
    private final int FLATROOFSMALLBREAKPOINT = 3600 - 150;
    private final int ROOFPLATEWIDTH = 1090;
    private final int ROOFPLATEOVERLAP = 110;

    private int bomId = 0; //bliver auto genereret

    public void calculate(int orderId, Inquiry inquiry, ConnectionPool connectionPool) throws DatabaseException {
        int carpLength = inquiry.getCarpLength();
        int carpWidth = inquiry.getCarpWidth();
        int shedWidth = inquiry.getShedWidth();
        int shedLength = inquiry.getShedLength();

        List<BillsOfMaterial> billsOfMaterials = new ArrayList<>();

        // FINDER ANTAL STOLPER PÅ BAGGRUND AF LÆNGDEN
        int carportColoumnAmount = getCarportColoumnAmount(carpLength);
        int shedColoumnAmount = getShedColoumnAmount(inquiry, carpWidth);
        int coloumnAmount = carportColoumnAmount + shedColoumnAmount;
        // Stolpe = materialID 4
        billsOfMaterials.add(new BillsOfMaterial(bomId, 4, orderId, coloumnAmount, "Stolper nedgraves 90 cm. i jord"));

        // FINDER REMME
        calcRemAmount(orderId, carpLength, billsOfMaterials, connectionPool);

        // FINDER SPÆR //TODO: afstanden spærrene skal sættes op med skal gemmes til vejledningen/tegningen
        int raftersAmount = getRaftersAmount(orderId, carpLength, carpWidth, billsOfMaterials, connectionPool);

        //BEREGNING AF TAGPLADER
        int slope = inquiry.getRoofSlope();
        if (slope == 0) { //Fladt tag
            calcRoofPlateAmount(orderId, carpLength, carpWidth, inquiry, billsOfMaterials, connectionPool);
        } else { //Tag med rejsning
            calcRoofTiles(orderId, inquiry, connectionPool, carpLength, carpWidth, billsOfMaterials, slope);
        }

        //SIDSTE BRÆDDER HVOR LÆNGDEN KAN VARIERE
        //******************** til carportens bredde ****************
        calcFasciaBoardsForCarpWidth(orderId, carpWidth, billsOfMaterials, connectionPool);
        calcOuterFasciaBoardsForCarpWidth(orderId, carpWidth, billsOfMaterials, connectionPool);
        calcWaterBoardForCarpWidth(orderId, carpWidth, billsOfMaterials, connectionPool);
        // ******************* til carpotens længde *******************
        calcFasciaBoardForCarpLength(orderId, carpLength, billsOfMaterials, connectionPool);
        calcOuterFasciaBoardForCarpLength(orderId, carpLength, billsOfMaterials, connectionPool);
        calcWaterBoardForCarpLength(orderId, carpLength, billsOfMaterials, connectionPool);

        //DELE DER ALTID SKAL MED
        addMiscallaneous(orderId, shedWidth, raftersAmount, carportColoumnAmount, shedLength, inquiry, billsOfMaterials);

        //SÆTTE IND I DATABASEN
        List<BillsOfMaterial> bomListWithId = Facade.insertBOMList(billsOfMaterials, connectionPool);
    }

    private void calcRoofTiles(int orderId, Inquiry inquiry, ConnectionPool connectionPool, int carpLength, int carpWidth, List<BillsOfMaterial> billsOfMaterials, int slope) throws DatabaseException {
        //Henter valgte tagsten fra databasen
        List<Materials> roofTilesList = Facade.getMaterialsByType(inquiry.getRoofType(), connectionPool);
        int roofTileId = roofTilesList.get(0).getMaterialId();
        //Beregner tagstenenes areal, til beregning af hvor mange der skal bruges
        int squareTile = roofTilesList.get(0).getLength() * roofTilesList.get(0).getWidth();
        //Hvor meget taget skal stikke ud over carporten
        int roofOverlay = 50 + 50;
        double halfCarpWidth = (carpWidth + roofOverlay) / 2;
        //Trigonometri, finder hypotenusen ud fra en vinkel og en side, for at finde ud af hvor lang den løftede side på taget er
        double slopeInRadians = Math.toRadians(slope);
        double roofWidthDouble = halfCarpWidth / Math.cos(slopeInRadians);
        int roofWidth = (int) Math.ceil(roofWidthDouble);
        int roofLength = carpLength + roofOverlay;
        int squareRoof = roofLength * roofWidth;
        //Tagstenene overlapper hinanden og der er to tagsider
        double overlapFactor = 1.5;
        double roofTileAmountDouble = (squareRoof / squareTile) * overlapFactor * 2;
        int roofTileAmount = (int) Math.ceil(roofTileAmountDouble);
        billsOfMaterials.add(new BillsOfMaterial(bomId, roofTileId, orderId, roofTileAmount, "Tagsten til taget"));
        //Rygningstenene
        //Finder rygningssten i samme farve som teglstenene
        Materials rooftopTiles = Facade.getRoofTopTile(inquiry.getRoofType(), connectionPool);
        int roofTopTileID = rooftopTiles.getMaterialId();
        //Beregner hvor mange rygningssten der skal bruges
        int topTileLength = rooftopTiles.getLength();
        double roofTopTileAmountDouble = (roofLength / topTileLength) * overlapFactor;
        int roofTopTileAmount = (int) Math.ceil(roofTopTileAmountDouble);
        billsOfMaterials.add(new BillsOfMaterial(bomId, roofTopTileID, orderId, roofTopTileAmount, "Rygsten til taget"));
        billsOfMaterials.add(new BillsOfMaterial(bomId, 31, orderId, roofTopTileAmount, "Rygstensbeslag"));
        billsOfMaterials.add(new BillsOfMaterial(bomId, 32, orderId, 1, "Tagstensbinder"));
        //Nogle brædder til at bygge tag
        int roofBoardAmount = 10;
        billsOfMaterials.add(new BillsOfMaterial(bomId, 57, orderId, roofBoardAmount, "Brædder til taget"));
    }

    private void calcWaterBoardForCarpLength(int orderId, int carpLength, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
//henter alle længder af vandbræt fra 'lageret', sorteret med de korteste først
        List<Materials> materialsList = Facade.getMaterialsByType("19x100mm. trykimp. Bræt", connectionPool);
        int materialId = 0;
        int waterBoardAmount = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten, de skal stikke 2,5 cm udover i hve ende, deraf de 50
        for (Materials materials : materialsList) {
            if (materials.getLength() >= carpLength + 50) {
                materialId = materials.getMaterialId();
                waterBoardAmount = 1;
                break;
            }
        }
        //Hvis ingen af vandbrættene var længere end carporten, tager vi en tur til på samme måde,
        if (materialId == 0) {
            for (Materials materials : materialsList) {
                if (materials.getLength() > (carpLength + 50) / 2) {
                    materialId = materials.getMaterialId();
                    waterBoardAmount = 2;
                    break;
                }
            }
        }
//        19x100	mm.	trykimp.	Bræt		 540 4 Stk vandbræt	på	stern	i	sider
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, waterBoardAmount, "Vandbræt på stern i sider"));
        // materialeId 8 l: 480,  materialeId 9 l: 240,  materialeId 10 l: 210,  materialeId 60 l: 360
    }

    private void calcOuterFasciaBoardForCarpLength(int orderId, int carpLength, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
//henter alle længder af oversternbrædder fra 'lageret', sorteret med de korteste først
        List<Materials> materialsList = Facade.getMaterialsByType("25x125 mm. trykimp. Bræt", connectionPool);
        int materialId = 0;
        int fasciaBoardAmount = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten, de skal stikke 2,5 cm udover i hve ende, deraf de 50
        for (Materials materials : materialsList) {
            if (materials.getLength() >= carpLength + 50) {
                materialId = materials.getMaterialId();
                fasciaBoardAmount = 1;
                break;
            }
        }
        //Hvis ingen af oversternbrædderne var længere end carporten, tager vi en tur til på samme måde,
        if (materialId == 0) {
            for (Materials materials : materialsList) {
                if (materials.getLength() > (carpLength + 50) / 2) {
                    materialId = materials.getMaterialId();
                    fasciaBoardAmount = 2;
                    break;
                }
            }
        }
        //        25x125mm.	trykimp.	Bræt 540 4 Stk oversternbrædder	til	siderne
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, fasciaBoardAmount, "Oversternbrædder til siderne"));
        // materialeId 57 l: 360
    }

    private void calcFasciaBoardForCarpLength(int orderId, int carpLength, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
//henter alle længder af understernbrædder fra 'lageret', sorteret med de korteste først
        List<Materials> materialsList = Facade.getMaterialsByType("25x200 mm. trykimp. Bræt (Birk)", connectionPool);
        int materialId = 0;
        int fasciaBoardAmount = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten, de skal stikke 2,5 cm udover i hve ende, deraf de 50
        for (Materials materials : materialsList) {
            if (materials.getLength() >= carpLength + 50) {
                materialId = materials.getMaterialId();
                fasciaBoardAmount = 1;
                break;
            }
        }
        //Hvis ingen af understernbrædderne var længere end carporten, tager vi en tur til på samme måde,
        if (materialId == 0) {
            for (Materials materials : materialsList) {
                if (materials.getLength() > (carpLength + 50) / 2) {
                    materialId = materials.getMaterialId();
                    fasciaBoardAmount = 2;
                    break;
                }
            }
        }
        //        25x200	mm.	trykimp.	Bræt 540 4 Stk understernbrædder	til	siderne
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, fasciaBoardAmount, "Understernbrædder til siderne"));
        // materialeId 55 l: 360
    }

    private void calcWaterBoardForCarpWidth(int orderId, int carpWidth, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
//henter alle længder af vandbræt fra 'lageret', sorteret med de korteste først
        List<Materials> materialsList = Facade.getMaterialsByType("19x100mm. trykimp. Bræt", connectionPool);
        int materialId = 0;
        int waterBoardAmount = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten, de skal stikke 2,5 cm udover i hve ende, deraf de 50
        for (Materials materials : materialsList) {
            if (materials.getLength() >= carpWidth + 50) {
                materialId = materials.getMaterialId();
                waterBoardAmount = 1;
                break;
            }
        }
        //Hvis ingen af vandbrættene var længere end carporten, tager vi en tur til på samme måde,
        if (materialId == 0) {
            for (Materials materials : materialsList) {
                if (materials.getLength() > (carpWidth + 50) / 2) {
                    materialId = materials.getMaterialId();
                    waterBoardAmount = 2;
                    break;
                }
            }
        }
        //        19x100 mm. trykimp. Bræt 360 2 Stk vandbræt på stern i forende
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, waterBoardAmount, "Vandbræt på stern i forende"));
        // materialeId 8 l: 480,  materialeId 9 l: 240,  materialeId 10 l: 210,  materialeId 59 l: 540
    }

    private void calcOuterFasciaBoardsForCarpWidth(int orderId, int carpWidth, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
//henter alle længder af oversternbrædder fra 'lageret', sorteret med de korteste først
        List<Materials> materialsList = Facade.getMaterialsByType("25x125 mm. trykimp. Bræt", connectionPool);
        int materialId = 0;
        int fasciaBoardAmount = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten, de skal stikke 2,5 cm udover i hve ende, deraf de 50
        for (Materials materials : materialsList) {
            if (materials.getLength() >= carpWidth + 50) {
                materialId = materials.getMaterialId();
                fasciaBoardAmount = 1;
                break;
            }
        }
        //Hvis ingen af oversternbrædderne var længere end carporten, tager vi en tur til på samme måde,
        if (materialId == 0) {
            for (Materials materials : materialsList) {
                if (materials.getLength() > (carpWidth + 50) / 2) {
                    materialId = materials.getMaterialId();
                    fasciaBoardAmount = 2;
                    break;
                }
            }
        }
        //        25x125mm.	trykimp.	Bræt 360 2 Stk oversternbrædder	til	forenden
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, fasciaBoardAmount, "Oversternbrædder til forenden"));
        // materialeId 58 er samme men med længden 540
    }

    private void calcFasciaBoardsForCarpWidth(int orderId, int carpWidth, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
        //henter alle længder af understernbrædder fra 'lageret', sorteret med de korteste først
        List<Materials> materialsList = Facade.getMaterialsByType("25x200 mm. trykimp. Bræt (Birk)", connectionPool);
        int materialId = 0;
        int fasciaBoardAmount = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten, de skal stikke 2,5 cm udover i hve ende, deraf de 50
        for (Materials materials : materialsList) {
            if (materials.getLength() >= carpWidth + 50) {
                materialId = materials.getMaterialId();
                fasciaBoardAmount = 2;
                break;
            }
        }
        //Hvis ingen af understernbrædderne var længere end carporten, tager vi en tur til på samme måde,
        if (materialId == 0) {
            for (Materials materials : materialsList) {
                if (materials.getLength() > (carpWidth + 50) / 2) {
                    materialId = materials.getMaterialId();
                    fasciaBoardAmount = 4;
                    break;
                }
            }
        }
//        25x200	mm.	trykimp.	Bræt 360 4 Stk understernbrædder	til	for	&	bag	ende
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, fasciaBoardAmount, "Understernbredder til for & bagende"));
        // materialeId 56 er samme men med længden 540
    }

    private void addMiscallaneous(int orderId, int shedWidth, int raftersAmount, int carportColoumnAmount, int shedLength, Inquiry inquiry, List<BillsOfMaterial> billsOfMaterials) {
        //        plastmo	bundskruer	200	stk. 3 pakke Skruer	til	tagplader
        if(inquiry.getRoofSlope() == 0) {
            billsOfMaterials.add(new BillsOfMaterial(bomId, 22, orderId, 3, "Skruer til tagplader"));
        }
//        hulbånd	1x20	mm.	10	mtr. 2 Rulle Til	vindkryds	på	spær
        billsOfMaterials.add(new BillsOfMaterial(bomId, 23, orderId, 2, "Til vindkryds på spær"));

//        4,5	x	60	mm.	skruer	200	stk. 1 Pakke Til	montering	af	stern&vandbræt
        billsOfMaterials.add(new BillsOfMaterial(bomId, 20, orderId, 1, "Til montering af stern & vandbræt"));

//        4,0	x	50	mm.	beslagskruer	250 stk. 3 pakke Til	montering	af	universalbeslag	+	hulbånd
        billsOfMaterials.add(new BillsOfMaterial(bomId, 21, orderId, 3, "Til montering af universalbeslag + hulbånd"));

        if (shedWidth > 0) {
//        38x73	mm.	Lægte ubh. 420 1 stk Til z på bagside af dør
            billsOfMaterials.add(new BillsOfMaterial(bomId, 13, orderId, 1, "Til z på bagside af dør"));
//        4,5 x 70 mm. Skruer 400 stk. 2 pk. Til montering af yderste beklædning
            billsOfMaterials.add(new BillsOfMaterial(bomId, 26, orderId, 2, "Til montering af yderste beklædning"));
//        4,5 x 50 mm. Skruer 300 stk. 2 pk. Til montering af inderste beklædning
            billsOfMaterials.add(new BillsOfMaterial(bomId, 27, orderId, 2, "Til montering af inderste beklædning"));
//        stalddørsgreb	50x75 1 Sæt Til lås på dør i skur
            billsOfMaterials.add(new BillsOfMaterial(bomId, 17, orderId, 1, "Til lås på dør i skur"));
//        t	hængsel	390	mm 2 Stk Til skurdør
            billsOfMaterials.add(new BillsOfMaterial(bomId, 18, orderId, 2, "Til skurdør"));
        }
        //DELE HVOR ANTALLET KAN VARIERE

        //Universal 190 mm ses nederst på side 4, og der skal være lige mange som der er spær. En højre og en venstre
        int universalRight = raftersAmount;
        int universalLeft = universalRight;
        billsOfMaterials.add(new BillsOfMaterial(bomId, 15, orderId, universalRight, "Til montering af spær på rem"));
        billsOfMaterials.add(new BillsOfMaterial(bomId, 16, orderId, universalLeft, "Til montering af spær på rem"));

//        firkantskiver	40x40x11mm 12 Stk Til montering	af	rem	på	stolper
        // 2 pr carportstolpe, ikke dem der kun er til skur
        int squareWasher = carportColoumnAmount * 2;
        billsOfMaterials.add(new BillsOfMaterial(bomId, 25, orderId, squareWasher, "Til montering af rem på stolper"));

        // 3 pr carportstolpe, ikke dem der kun er til skur
//        bræddebolt	10	x	120	mm.	 18 Stk Til	montering	af	rem	på	stolper
        int carriageBolt = carportColoumnAmount * 3;
        billsOfMaterials.add(new BillsOfMaterial(bomId, 24, orderId, carriageBolt, "Til montering af rem på stolper"));

        //**Skur**
//        45x95	mm.	Reglar ub. 270 12 stk Løsholter til skur gavle ---ikke nogen i DB med den længde. id 7 er 360 cm
        if (shedWidth > 0) {
            int noggingGables = 12;
            if (shedWidth <= 3600) {
                noggingGables = 6;
            }
            billsOfMaterials.add(new BillsOfMaterial(bomId, 7, orderId, noggingGables, "Løsholter til skur gavle"));

//        45x95	mm.	Reglar ub. 240 4 stk Løsholter	til	skur sider
            double noggingSidesFactor = (double) shedLength / 2400;
            int nSFactor = (int) Math.ceil(noggingSidesFactor);
            int noggingSides = nSFactor * 4;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 6, orderId, noggingSides, "Løsholter til skur sider"));

//        vinkelbeslag 35 32 Stk Til montering af løsholter i skur //2*samlede antal løsholter
            int angleFitting = (noggingGables + noggingSides) * 2;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 19, orderId, angleFitting, "Til montering af løsholter i skur"));

//        19x100 mm. trykimp. Bræt 210 200 stk Til beklædning af skur 1 på 2
            //beklædning af skuret: skurets omkreds divideret med en faktor der passer
            int cladding = (shedLength * 2 + shedWidth * 2) / 80;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 10, orderId, cladding, "Til beklædning af skur 1 på 2"));
        }
    }

    private void calcRoofPlateAmount(int orderId, int carpLength, int carpWidth, Inquiry inquiry, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
        //bølgerne på tagpladerne går på langs med pladernes længde. Og bølgerne skal følge tagets hældning, så vandet kan løbe af skuret.
        // taget skal gå 5 cm ud over sternbredderne på alle leder, derfor lægges 10 cm til
        int roofLength = carpLength + 100;
        int roofWidth = carpWidth + 100;

        int roofPlateWidthMinusOverlap = ROOFPLATEWIDTH - ROOFPLATEOVERLAP;

        // overlappet mellem pladerne på den korte led er 11 cm, da godt 1,5 bølger skal overlappe og der er 7 cm fra bølgetop til bølgetop
        // den sidste plade har sin fulde bredde
        // overlappet på den lange led skal være 20 cm

        double plateAmountDouble = ((double) roofWidth - ROOFPLATEOVERLAP) / roofPlateWidthMinusOverlap;
        int plateAmount = (int) Math.ceil(plateAmountDouble);
        int shortRoofPlatesAmount = 0;
        String roofType = inquiry.getRoofType();
        int materialIdLong = Facade.getMaterialsByType(roofType, connectionPool).get(1).getMaterialId();
        int materialIdShort = Facade.getMaterialsByType(roofType, connectionPool).get(0).getMaterialId();

        if (roofLength <= FLATROOFSMALLBREAKPOINT) {
            shortRoofPlatesAmount = plateAmount;
            billsOfMaterials.add(new BillsOfMaterial(bomId, materialIdShort, orderId, shortRoofPlatesAmount, "Tagplader monteres på spær"));
        } else {

            if (roofLength > FLATROOFBIGBREAKPOINT) {
                shortRoofPlatesAmount = plateAmount;
                billsOfMaterials.add(new BillsOfMaterial(bomId, materialIdShort, orderId, shortRoofPlatesAmount, "Tagplader monteres på spær"));
            }

            int longRoofPlatesAmount = plateAmount;
            billsOfMaterials.add(new BillsOfMaterial(bomId, materialIdLong, orderId, longRoofPlatesAmount, "Tagplader monteres på spær"));
        }
    }

    public int getRaftersAmount(int orderId, int carpLength, int carpWidth, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {

        //        Ny længde = Længde på carport - tykkelse på spær - (tykkelse på understernbrædder * 2)
//        (fordi det første spærs tykkelse ikke er medregnet når mellemrummene lægges sammen, og der er er understernbræt på foran og bagved)

        int newLength = carpLength - RAFTERSTHICKNESS - (FASCIABOARDTHICKNESS * 2);

//        Antal mellemrum mellem spær = ny længde / max afstand mellem spær

        //        Antal mellemrum mellem spær = 7705 mm / 600 mm = 12,841
        double spaceAmountBetweenRaftersDouble = (double) newLength / MAXDISTANCERAFTERS;

        //        Så runder vi op, fordi det var en maxafstand.
//        Så det vil sige ved 13 mellemrum skal vi bruge 14 spær
        int spaceAmountBetweenRafters = (int) Math.ceil(spaceAmountBetweenRaftersDouble);

        //Der skal altid være et spær mere end antallet af mellemrum mellem spær
        int raftersAmount = spaceAmountBetweenRafters + 1;

//        Afstand mellem spær = 7705 mm / 13 = 592,7 mm

        //TODO: denne afstand spærrene skal sættes med skal også gemmes, så den kan komme med på tegningen og i vejledningen.

        double raftersDistance = (double) newLength / spaceAmountBetweenRafters;

        //Finde den rette længde på spærrene
        List<Materials> materialsList = Facade.getMaterialsByType("45x195 spærtræ ubh.", connectionPool);
        int materialId = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten
        for (Materials materials : materialsList) {
            if (materials.getLength() >= carpWidth) {
                materialId = materials.getMaterialId();
                break;
            }
        }
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, raftersAmount, "Spær, monteres på rem"));
        return raftersAmount;
    }

    private void calcRemAmount(int orderId, int carpLength, List<BillsOfMaterial> billsOfMaterials, ConnectionPool connectionPool) throws DatabaseException {
        int remAmount = 0;
        //henter alle længder af remme fra 'lageret', sorteret med de korteste først
        List<Materials> materialsList = Facade.getMaterialsByType("45x195 spærtræ ubh.", connectionPool);
        int materialId = 0;
        //vælger den bedste længde til opgaven. her bare den første der er længere end carporten
        for (Materials materials : materialsList) {
            if (materials.getLength() > carpLength) {
                materialId = materials.getMaterialId();
                remAmount = 2;
                break;
            }
        }
        //Hvis ingen af remmene var længere end carporten, tager vi en tur til på samme måde,
        // men ser hvilken rem der er længere end den halve længde af carporten,
        // således at samlingen af de to remme kan ligge ved midterstolpen
        if (materialId == 0) {
            for (Materials materials : materialsList) {
                if (materials.getLength() > carpLength / 2) {
                    materialId = materials.getMaterialId();
                    remAmount = 4;
                    break;
                }
            }
        }
        billsOfMaterials.add(new BillsOfMaterial(bomId, materialId, orderId, remAmount, "Remme i sider, sadles ned i stolper"));
    }

    public int getCarportColoumnAmount(int carpLength) {
        int carportColoumnAmount = 0;
        if (carpLength <= COLUMNBREAKPOINT) {
            carportColoumnAmount = 4;
        } else {
            carportColoumnAmount = 6;
        }
        return carportColoumnAmount;
    }

    public int getShedColoumnAmount(Inquiry inquiry, int carpWidth) {
        //HVis der er skur, så 5 stolper ekstra, hvis skuret er i halv bredde, så kun 4 ekstra. Den ene stolpe er til skurdøren.
        int shedColoumnAmount = 0;
        if (inquiry.getShedWidth() > 0) {
            if (inquiry.getShedWidth() <= carpWidth / 2) {
                shedColoumnAmount = 4;
            } else {
                shedColoumnAmount = 5;
            }
        }
        return shedColoumnAmount;
    }
}
