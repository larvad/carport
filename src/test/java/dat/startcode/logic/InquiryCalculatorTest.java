package dat.startcode.logic;

import dat.startcode.model.entities.BillsOfMaterial;
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

    @BeforeEach
    void setUp() {
    }

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


}