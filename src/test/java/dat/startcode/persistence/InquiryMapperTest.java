package dat.startcode.persistence;

import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class InquiryMapperTest {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
    }

    @BeforeEach
    void setUp() {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement()) {
                // Remove all rows from all tables
                stmt.execute("delete from carport_test.inquiry");
                // Indsæt et par inquiries
                stmt.execute("insert into carport_test.inquiry (inquiry_id, carp_width, carp_length, roof_type, roof_slope, shed_width, shed_length, timestamp) " +
                        "values ('0', 6000, 7800, 'fladt', 0, 6000, 3000, NOW()),('0', 3000, 5000, 'fladt', 0, 1500, 2000, NOW())");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    //VIRKER IKKE, henter fra den almindelige DB og ikke test, plus problem med ID
    void getInquiryById() throws DatabaseException {
        Inquiry newInquiry = Facade.getInquiryById(64, connectionPool);
        Inquiry expectedInquiry = new Inquiry(68, 3000, 5000, "fladt", 0, 1500, 2000);
        assertEquals(expectedInquiry, newInquiry);
    }

    @Test
    //Ignorerer inquiryId og timestamp
    void insertInquiryIntoDB() throws DatabaseException {
        Inquiry newInquiry = Facade.insertInquiryIntoDB(4000, 6000, "fladt", 0, 2000, 2000, connectionPool);
        Inquiry expectedInquiry = new Inquiry(0, 4000, 6000, "fladt", 0, 2000, 2000);
        assertEquals(expectedInquiry, newInquiry);
    }

    @Test
    void updateInquiryByInquiryId() {
    }
}