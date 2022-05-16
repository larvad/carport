package dat.startcode.logic;

import dat.startcode.model.dto.MaterialDTO;
import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.BillsOfMaterialMapper;
import dat.startcode.model.persistence.ConnectionPool;

import dat.startcode.model.entities.Inquiry;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class RequestCalculator {
    /* 100 cm udhæng foran + maks afstand på 325 cm mellem stolper + 30 cm udhæng bagtil
     Derfor følgende breakpoint */
    //STOLPER
    private final int COLUMNBREAKPOINT = 4550;
    //REMME
    private final int REMBREAKPOINT = 4800 - 200;
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

    //TODO: Hvis det skal være rigtigt skal koden jo egentlig søge de bedste brædder til opgaven frem i databasen (ellers får admin heller ikke noget ud af at tilføje nye materialer)
    // Det kan vi vælge bare at gøre på en af brædderne, for at vise

    public void calculate(int orderId, Inquiry inquiry, ConnectionPool connectionPool) throws DatabaseException {
        int carpLength = inquiry.getCarpLength();
        int carpWidth = inquiry.getCarpWidth();
        int shedWidth = inquiry.getShedWidth();
        int shedLength = inquiry.getShedLength();

        List<BillsOfMaterial> billsOfMaterials = new ArrayList<>();

        // FINDER ANTAL STOLPER PÅ BAGGRUND AF LÆNGDEN
        int coloumnAmount = getColoumnAmount(inquiry, orderId, carpLength, carpWidth, billsOfMaterials);

        // FINDER REMME //TODO: tager ikke højde for at der er remme i forskellige længder
         calcRemAmount(orderId, carpLength, billsOfMaterials);

        // FINDER SPÆR //TODO: tager pt ikke højde for at man kan få spær i forskellige længder og at afstanden spærrene skal sættes op med skal gemmes til vejledningen
        int raftersAmount = getRaftersAmount(orderId, carpLength, billsOfMaterials);

        //BEREGNING AF TAGPLADER
        calcRoofPlateAmount(orderId, carpLength, carpWidth, billsOfMaterials);

        //SIDSTE BRÆDDER HVOR LÆNGDEN KAN VARIERE
        //TODO: ikke fixet med de forskellige længder og bredder carport
        //******************** BREDDE ****************
//        25x200	mm.	trykimp.	Bræt 360 4 Stk understernbrædder	til	for	&	bag	ende //TODO: ken vel nøjes med to ved en smal carport?
        billsOfMaterials.add(new BillsOfMaterial(bomId, 55, orderId, 4, "Understernbredder til for & bagende"));
        // materialeId 56 er samme men med længden 540

//        25x125mm.	trykimp.	Bræt 360 2 Stk oversternbrædder	til	forenden
        billsOfMaterials.add(new BillsOfMaterial(bomId, 57, orderId, 2, "Oversternbrædder til forenden"));
        // materialeId 58 er samme men med længden 540

        //        19x100	mm.	trykimp.	Bræt		 360 2 Stk vandbræt	på	stern	i	forende
        billsOfMaterials.add(new BillsOfMaterial(bomId, 60, orderId, 2, "Vandbræt på stern i forende"));
        // materialeId 8 l: 480,  materialeId 9 l: 240,  materialeId 10 l: 210,  materialeId 59 l: 540


        // ******************* LÆNGDE *******************
//        25x200	mm.	trykimp.	Bræt 540 4 Stk understernbrædder	til	siderne
        billsOfMaterials.add(new BillsOfMaterial(bomId, 56, orderId, 4, "Understernbrædder til siderne"));
        // materialeId 55 l: 360

//        25x125mm.	trykimp.	Bræt 540 4 Stk oversternbrædder	til	siderne
        billsOfMaterials.add(new BillsOfMaterial(bomId, 58, orderId, 4, "Oversternbrædder til siderne"));
        // materialeId 57 l: 360

//        19x100	mm.	trykimp.	Bræt		 540 4 Stk vandbræt	på	stern	i	sider
        billsOfMaterials.add(new BillsOfMaterial(bomId, 59, orderId, 4, "Vandbræt på stern i sider"));
        // materialeId 8 l: 480,  materialeId 9 l: 240,  materialeId 10 l: 210,  materialeId 60 l: 360


        //DELE HVOR ANTALLET KAN VARIERE

        //Universal 190 mm ses nederst på side 4, og der skal være lige mange som der er spær. En højre og en venstre
        int universalRight = raftersAmount;
        int universalLeft = universalRight;
        billsOfMaterials.add(new BillsOfMaterial(bomId, 15, orderId, universalRight, "Til montering af spær på rem"));
        billsOfMaterials.add(new BillsOfMaterial(bomId, 16, orderId, universalLeft, "Til montering af spær på rem"));

//        firkantskiver	40x40x11mm 12 Stk Til montering	af	rem	på	stolper
        // TODO: 2 pr stolpe når der ikke er skur med, så, beregningen duer ikke når der er skur på
        int squareWasher = coloumnAmount * 2;
        billsOfMaterials.add(new BillsOfMaterial(bomId, 25, orderId, squareWasher, "Til montering af rem på stolper"));

        // TODO: 3 pr stolpe når der ikke er skur med, så, beregningen duer ikke når der er skur på
//        bræddebolt	10	x	120	mm.	 18 Stk Til	montering	af	rem	på	stolper
        int carriageBolt = coloumnAmount * 3;
        billsOfMaterials.add(new BillsOfMaterial(bomId, 24, orderId, carriageBolt, "Til montering af rem på stolper"));

        //TODO delene til skuret skal også med.
        //Antallet kan variere
//        45x95	mm.	Reglar ub. 270 12 stk Løsholter til skur gavle
        //ALtid 4 nedenfor
//        45x95	mm.	Reglar ub. 240 4 stk Løsholter	til	skur sider
//        vinkelbeslag 35 32 Stk Til montering af løsholter i skur //2*samlede antal løsholter
//
//        19x100 mm. trykimp. Bræt 210 200 stk Til beklædning af skur 1 på 2

        //DELE DER ALTID SKAL MED
        addMiscallaneous(orderId, shedWidth, billsOfMaterials);

        //SÆTTE IND I DATABASEN
        BillsOfMaterialMapper billsOfMaterialMapper = new BillsOfMaterialMapper(connectionPool);
        billsOfMaterialMapper.insertBOMList(billsOfMaterials);


    }

    private void addMiscallaneous(int orderId, int shedWidth, List<BillsOfMaterial> billsOfMaterials) {
        //        plastmo	bundskruer	200	stk. 3 pakke Skruer	til	tagplader
        billsOfMaterials.add(new BillsOfMaterial(bomId, 22, orderId, 3, "Skruer til tagplader"));

//        hulbånd	1x20	mm.	10	mtr. 2 Rulle Til	vindkryds	på	spær
        billsOfMaterials.add(new BillsOfMaterial(bomId, 23, orderId, 2, "Til vindkryds på spær"));

//        4,5	x	60	mm.	skruer	200	stk. 1 Pakke Til	montering	af	stern&vandbræt
        billsOfMaterials.add(new BillsOfMaterial(bomId, 20, orderId, 1, "Til montering af stern & vandbræt"));

//        4,0	x	50	mm.	beslagskruer	250 stk. 3 pakke Til	montering	af	universalbeslag	+	hulbånd
        billsOfMaterials.add(new BillsOfMaterial(bomId, 21, orderId, 3, "Til montering af universalbeslag + hulbånd"));

        if(shedWidth > 0) {
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
    }

    private void calcRoofPlateAmount(int orderId, int carpLength, int carpWidth, List<BillsOfMaterial> billsOfMaterials) {
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

        if (roofLength <= FLATROOFSMALLBREAKPOINT) {
            shortRoofPlatesAmount = plateAmount;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 29, orderId, shortRoofPlatesAmount, "Tagplader monteres på spær"));
        } else {

            if (roofLength > FLATROOFBIGBREAKPOINT) {
                shortRoofPlatesAmount = plateAmount;
                billsOfMaterials.add(new BillsOfMaterial(bomId, 29, orderId, shortRoofPlatesAmount, "Tagplader monteres på spær"));
            }

            int longRoofPlatesAmount = plateAmount;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 28, orderId, longRoofPlatesAmount, "Tagplader monteres på spær"));
        }
    }

    private void calcRemAmount(int orderId, int carpLength, List<BillsOfMaterial> billsOfMaterials) {
        int remAmount = 0;

        if (carpLength <= REMBREAKPOINT) {
            remAmount = 2;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 5, orderId, remAmount, "Remme i sider, sadles ned i stolper"));

        } else {
            remAmount = 4;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 5, orderId, remAmount, "Remme i sider, sadles ned i stolper"));
        }
    }

    private int getColoumnAmount(Inquiry inquiry, int orderId, int carpLength, int carpWidth, List<BillsOfMaterial> billsOfMaterials) {
        int coloumnAmount = 0;
        if (carpLength <= COLUMNBREAKPOINT) {
            // Stolpe = materialID 4
            coloumnAmount = 4;
        } else {
            coloumnAmount = 6;
        }
        //HVis der er skur, så 5 stolper ekstra, hvis skuret er i halv bredde, så kun 4 ekstra. Den ene stolpe er til skurdøren.
        if (inquiry.getShedWidth() <= carpWidth / 2) {
            coloumnAmount += 4;
        } else if (inquiry.getShedLength() > 0) {
            coloumnAmount += 5;
        }

        billsOfMaterials.add(new BillsOfMaterial(bomId, 4, orderId, coloumnAmount, "Stolper nedgraves 90 cm. i jord"));
        return coloumnAmount;
    }

    private int getRaftersAmount(int orderId, int carpLength, List<BillsOfMaterial> billsOfMaterials) {

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

        billsOfMaterials.add(new BillsOfMaterial(bomId, 54, orderId, raftersAmount, "Spær, monteres på rem"));
        return raftersAmount;
    }


}

