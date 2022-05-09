package dat.startcode.logic;

import dat.startcode.model.dto.MaterialDTO;
import dat.startcode.model.entities.CustomerRequest;

import java.util.ArrayList;
import java.util.List;

public class RequestCalculator {

    CustomerRequest customerRequest = new CustomerRequest(600, 780, "flat");


    /* 100 cm udhæng foran + maks afstand på 325 cm mellem stolper + 30 cm udhæng bagtil
     Derfor følgende breakpoint */
    private final int COLUMNBREAKPOINT = 455;
    private final int REMBREAKPOINT = 480;

    public void calculate(CustomerRequest customerRequest) {

        List<MaterialDTO> listOfMaterial = new ArrayList<>();

        // Finder antal stolper på baggrund af længden

        if (customerRequest.getCarpLength() <= COLUMNBREAKPOINT) {

            // Stolpe = materialID 4

            listOfMaterial.add(new MaterialDTO(4,4));

        }
        else {
            listOfMaterial.add(new MaterialDTO(4, 6));
        }

        // Finder remme

        if (customerRequest.getCarpLength() <= REMBREAKPOINT) {

            listOfMaterial.add(new MaterialDTO(5,2));

        }
        else {

            listOfMaterial.add(new MaterialDTO(5,4));
        }

        // Finder spær




    }




}
