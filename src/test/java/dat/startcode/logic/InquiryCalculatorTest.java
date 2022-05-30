package dat.startcode.logic;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InquiryCalculatorTest {

    @Test
    void getRaftersAmount() throws DatabaseException {
            InquiryCalculator inquiryCalculator = new InquiryCalculator();
            List<BillsOfMaterial> billsOfMaterialList = new ArrayList<>();
            ConnectionPool connectionPool = new ConnectionPool();
        int expectedAmount1 = 11;
        int actualAmount1 = inquiryCalculator.getRaftersAmount(0, 6000, 6000, billsOfMaterialList, connectionPool);
        assertEquals(expectedAmount1, actualAmount1);
        int expectedAmount2 = 6;
        int actualAmount2 = inquiryCalculator.getRaftersAmount(0, 3000, 3000, billsOfMaterialList, connectionPool);
        assertEquals(expectedAmount2, actualAmount2);

    }

    @Test
    void getCarportColoumnAmount() {
        InquiryCalculator inquiryCalculator = new InquiryCalculator();
        int expectedAmount1 = 6;
        int actualAmount1 = inquiryCalculator.getCarportColoumnAmount(6000);
        assertEquals(expectedAmount1, actualAmount1);
        int expectedAmount2 = 4;
        int actualAmount2 = inquiryCalculator.getCarportColoumnAmount(3000);
        assertEquals(expectedAmount2, actualAmount2);

    }

    @Test
    void getShedColoumnAmount() {
        InquiryCalculator inquiryCalculator = new InquiryCalculator();
        Inquiry inquiry = new Inquiry(0, 6000, 6000, "fladt", 0, 3000, 3000);
        Inquiry inquiry2 = new Inquiry(0, 6000, 6000, "fladt", 0, 6000, 3000);
        int expectedAmount1 = 4;
        int actualAmount1 = inquiryCalculator.getShedColoumnAmount(inquiry, 6000);
        assertEquals(expectedAmount1, actualAmount1);
        int expectedAmount2 = 5;
        int actualAmount2 = inquiryCalculator.getShedColoumnAmount(inquiry2, 6000);
        assertEquals(expectedAmount2, actualAmount2);

    }


}