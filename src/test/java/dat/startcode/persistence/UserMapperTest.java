package dat.startcode.persistence;

import dat.startcode.model.entities.User;
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

class UserMapperTest {
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
                stmt.execute("delete from user");
                // Indsæt et par brugere
                stmt.execute("insert into user (user_id, username, email, password, role_id, phone_no, adress) " +
                        "values ('1','Bo Bobsen','b@b.dk','1234',2,11223344,'adresse 1'),('2','Ib Ibsen','a@a.dk','1234',1,42424242,'adresse 2')");
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
    void login() throws DatabaseException {
        User expectedUser = new User("Bo Bobsen", "1234", 2, '1', "b@b.dk", 11223344, "adresse 1");
        User actualUser = Facade.login("b@b.dk", "1234", connectionPool);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> Facade.login("user", "123", connectionPool));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException {
        assertThrows(DatabaseException.class, () -> Facade.login("bob", "1234", connectionPool));
    }

    @Test
    void createUser() throws DatabaseException {
        User newUser = Facade.createUser("Mango", "m@m.dk", "1234", 666, "adresse 3", connectionPool);
        User logInUser = Facade.login("m@m.dk", "1234", connectionPool);
        User expectedUser = new User("Mango", "1234", 1, 3, "m@m.dk", 666, "adresse 3");
        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);

    }
}