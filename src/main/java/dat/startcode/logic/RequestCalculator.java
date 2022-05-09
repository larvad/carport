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
    private final int COLUMNBREAKPOINT = 4550;
    private final int REMBREAKPOINT = 4800;
    private final int MAXDISTANCERAFTERS = 600;
    private final int RAFTERSTHICKNESS = 45;
    private final int UNDERSTERNBRÆDDERTYKKELSE = 25;

    //TODO: Hyggehejsa læser, dette skal ændres så det kommer fra et scope!
    private int orderId = 0;
    private int bomId = 0; //bliver auto gerenert


    // public void calculate(CustomerRequest customerRequest) {
    public void calculate() {

        List<MaterialDTO> listOfMaterial = new ArrayList<>();
        List<BillsOfMaterial> billsOfMaterials = new ArrayList<>();

        // Finder antal stolper på baggrund af længden

        if (carpLengthInMm <= COLUMNBREAKPOINT) {
            // Stolpe = materialID 4
            billsOfMaterials.add(new BillsOfMaterial(bomId, 4, orderId, 4, "Stolper nedgraves 90 cm. i jord"));

        } else {
            billsOfMaterials.add(new BillsOfMaterial(bomId, 4, orderId, 6, "Stolper nedgraves 90 cm. i jord"));
        }

        // Finder remme //TODO: tager ikke højde for at der er remme i forskellige længder

        if (carpLengthInMm <= REMBREAKPOINT) {
            billsOfMaterials.add(new BillsOfMaterial(bomId, 5, orderId, 2, "Remme i sider, sadles ned i stolper"));

        } else {
            billsOfMaterials.add(new BillsOfMaterial(bomId, 5, orderId, 4, "Remme i sider, sadles ned i stolper"));
        }

        // Finder spær //TODO: tager pt ikke højde for at man kan få spær i forskellige længder

//        Ny længde = Længde på carport - tykkelse på spær - (tykkelse på understernbrædder * 2) (fordi det første spærs tykkelse ikke er medregnet når mellemrummene lægges sammen, og der er er understernbræt på foran og bagved)

        int newLength = carpLengthInMm - RAFTERSTHICKNESS - (UNDERSTERNBRÆDDERTYKKELSE * 2);

//        Antal mellemrum mellem spær = ny længde / max afstand mellem spær

//        Antal mellemrum mellem spær = 7705 mm / 600 mm = 12,841
        double spaceAmountBetweenRaftersDouble = (double) newLength / MAXDISTANCERAFTERS;


//        Så skal man vel runde op, fordi det var en maxafstand?
//
//        Så det vil sige 13 mellemrum skal vi bruge og 14 spær

        int spaceAmountBetweenRafters = (int) Math.ceil(spaceAmountBetweenRaftersDouble);

        //Der skal altid være et spær mere end antallet af mellemrum mellem spær
        int raftersAmount = spaceAmountBetweenRafters + 1;

//        Afstand mellem spær = 7705 mm / 13 = 592,7 mm

        double raftersDistance = (double) newLength / spaceAmountBetweenRafters;

        billsOfMaterials.add(new BillsOfMaterial(bomId, 54, orderId, raftersAmount, "Spær, monteres på rem"));

        System.out.println();
        //TODO: Kald mapper der tager en List<BillsOfMaterials> som parameter, looper over dem og stempler dem ned i databasen.

    }


}
