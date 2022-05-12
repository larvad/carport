package dat.startcode.logic;

import dat.startcode.model.dto.MaterialDTO;
import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.CustomerRequest;

import java.util.ArrayList;
import java.util.List;

public class RequestCalculator {

    CustomerRequest customerRequest = new CustomerRequest(600, 780, "flat");

    // Ganger med 10 for at få længderne i mm
    int carpLengthInMm = customerRequest.getCarpLength() * 10;
    int carpWidthInMm = customerRequest.getCarpWidth() * 10;

    /* 100 cm udhæng foran + maks afstand på 325 cm mellem stolper + 30 cm udhæng bagtil
     Derfor følgende breakpoint */
    //STOLPER
    private final int COLUMNBREAKPOINT = 4550;
    //REMME
    private final int REMBREAKPOINT = 4800;
    //SPÆR
    private final int MAXDISTANCERAFTERS = 600;
    private final int RAFTERSTHICKNESS = 45;
    //UNDERSTERNBRÆDDER
    private final int FASCIABOARDTHICKNESS = 25;
    //TAG
    //TODO: der er faktisk to breakpoints, da carportens længde kan være helt nede på 240 cm
    private final int FLATROOFBIGBREAKPOINT = 5850;
    private final int FLATROOFSMALLBREAKPOINT = 3450;
    private final int ROOFPLATEWIDTH = 1090;
    private final int ROOFPLATEOVERLAP = 110;


    //TODO: Hyggehejsa læser, dette skal ændres så det kommer fra et scope!
    private int orderId = 0;
    private int bomId = 0; //bliver auto gerenert

    //TODO: Hvis det skal være rigtigt skal koden jo egentlig søge de bedste brædder til opgaven frem i databasen (ellers får admin heller ikke noget ud af at tilføje nye materialer)

    // public void calculate(CustomerRequest customerRequest) {
    public void calculate() {

        List<MaterialDTO> listOfMaterial = new ArrayList<>();
        List<BillsOfMaterial> billsOfMaterials = new ArrayList<>();

        // FINDER ANTAL STOLPER PÅ BAGGRUND AF LÆNGDEN

        if (carpLengthInMm <= COLUMNBREAKPOINT) {
            // Stolpe = materialID 4
            billsOfMaterials.add(new BillsOfMaterial(bomId, 4, orderId, 4, "Stolper nedgraves 90 cm. i jord"));

        } else {
            billsOfMaterials.add(new BillsOfMaterial(bomId, 4, orderId, 6, "Stolper nedgraves 90 cm. i jord"));
        }

        // FINDER REMME //TODO: tager ikke højde for at der er remme i forskellige længder

        if (carpLengthInMm <= REMBREAKPOINT) {
            billsOfMaterials.add(new BillsOfMaterial(bomId, 5, orderId, 2, "Remme i sider, sadles ned i stolper"));

        } else {
            billsOfMaterials.add(new BillsOfMaterial(bomId, 5, orderId, 4, "Remme i sider, sadles ned i stolper"));
        }

        // FINDER SPÆR //TODO: tager pt ikke højde for at man kan få spær i forskellige længder

//        Ny længde = Længde på carport - tykkelse på spær - (tykkelse på understernbrædder * 2) (fordi det første spærs tykkelse ikke er medregnet når mellemrummene lægges sammen, og der er er understernbræt på foran og bagved)

        int newLength = carpLengthInMm - RAFTERSTHICKNESS - (FASCIABOARDTHICKNESS * 2);

//        Antal mellemrum mellem spær = ny længde / max afstand mellem spær

//        Antal mellemrum mellem spær = 7705 mm / 600 mm = 12,841
        double spaceAmountBetweenRaftersDouble = (double) newLength / MAXDISTANCERAFTERS;


//        Så skal man vel runde op, fordi det var en maxafstand?
//        Så det vil sige 13 mellemrum skal vi bruge og 14 spær

        int spaceAmountBetweenRafters = (int) Math.ceil(spaceAmountBetweenRaftersDouble);

        //Der skal altid være et spær mere end antallet af mellemrum mellem spær
        int raftersAmount = spaceAmountBetweenRafters + 1;

//        Afstand mellem spær = 7705 mm / 13 = 592,7 mm

        //TODO: denne afstand spærrene skal sættes med skal også gemmes, så den kan komme med på tegningen og i vejledningen.

        double raftersDistance = (double) newLength / spaceAmountBetweenRafters;

        billsOfMaterials.add(new BillsOfMaterial(bomId, 54, orderId, raftersAmount, "Spær, monteres på rem"));

        //TODO: Kald mapper der tager en List<BillsOfMaterials> som parameter, looper over dem og stempler dem ned i databasen.

        //BEREGNING AF TAGPLADER

        //bølgerne på tagpladerne går på langs med pladernes længde. Og bølgerne skal følge tagets hældning, så vandet kan løbe af skuret.

        // taget skal gå 5 cm ud over sternbredderne på alle leder, derfor lægges 10 cm til
        int roofLength = carpLengthInMm + 100;
        int roofWidth = carpWidthInMm + 100;

        int roofPlateWidthMinusOverlap = ROOFPLATEWIDTH - ROOFPLATEOVERLAP;

        // overlappet mellem pladerne på den korte led er 11 cm, da godt 1,5 bølger skal overlappe og der er 7 cm fra bølgetop til bølgetop
        // den sidste plade har sin fulde bredde
        // overlappet på den lange led skal være 20 cm

        double plateAmountDouble = ((double) roofWidth - ROOFPLATEOVERLAP) / roofPlateWidthMinusOverlap;
        //TODO her kunne være en if der chekkede om decimaltallet er meeeget lille og det giver bedre mening at runde ned
        int plateAmount = (int) Math.ceil(plateAmountDouble);
        int shortRoofPlatesAmount = 0;

        if (roofLength > FLATROOFSMALLBREAKPOINT) {
            shortRoofPlatesAmount = plateAmount;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 29, orderId, shortRoofPlatesAmount, "tagplader monteres på spær"));
        } else {

            if (roofLength > FLATROOFBIGBREAKPOINT) {
                shortRoofPlatesAmount = plateAmount;
                billsOfMaterials.add(new BillsOfMaterial(bomId, 29, orderId, shortRoofPlatesAmount, "tagplader monteres på spær"));
            }

            int longRoofPlatesAmount = plateAmount;
            billsOfMaterials.add(new BillsOfMaterial(bomId, 28, orderId, longRoofPlatesAmount, "tagplader monteres på spær"));
        }


        //DELE DER ALTID SKAL MED
        //Universal 190 mm ses nederst på side 4, og der skal være lige mange som der er spær. En højre og en venstre
//        firkantskiver	40x40x11mm 12 Stk Til montering	af	rem	på	stolper //TODO: 2 pr stolpe når der ikke er skur med?


//        25x200	mm.	trykimp.	Brædt 360 4 Stk understernbrædder	til	for	&	bag	ende
//        25x200	mm.	trykimp.	Brædt 540 4 Stk understernbrædder	til	siderne
//        25x125mm.	trykimp.	Brædt 360 2 Stk oversternbrædder	til	forenden
//        25x125mm.	trykimp.	Brædt 540 4 Stk oversternbrædder	til	siderne

//        19x100	mm.	trykimp.	Brædt		 540 4 Stk vandbrædt	på	stern	i	sider
//        19x100	mm.	trykimp.	Brædt		 360 2 Stk vandbrædt	på	stern	i	forende

//        Beslag	&	Skruer
//        plastmo	bundskruer	200	stk. 3 pakke Skruer	til	tagplader
//        hulbånd	1x20	mm.	10	mtr. 2 Rulle Til	vindkryds	på	spær

//        4,5	x	60	mm.	skruer	200	stk. 1 Pakke Til	montering	af	stern&vandbrædt
//        4,0	x	50	mm.	beslagskruer	250
//        stk. 3 pakke Til	montering	af	universalbeslag	+	hulbånd
//        bræddebolt	10	x	120	mm.	 18 Stk Til	montering	af	rem	på	stolper



    }


}
