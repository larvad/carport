package dat.startcode.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.services.UserFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest
{
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/carport_test?serverTimezone=CET&allowPublicKeyRetrieval=true&useSSL=false";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setUpClass() {
            connectionPool = new ConnectionPool(USER, PASSWORD, URL);
    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement stmt = testConnection.createStatement() ) {
                // Remove all rows from all tables
                stmt.execute("delete from user");
                // Indsæt et par brugere
                stmt.execute("insert into user (user_id, username, email, password, role_id, phone_no, adresse) " +
                        "values ('1','Bo Bobsen','b@b.dk','1234',2,11223344,'adresse 1'),('2','Ib Ibsen','a@a.dk','1234',1,42424242,'adresse 2')");
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null)
        {
            connection.close();
        }
    }

    @Test
    void login() throws DatabaseException
    {
        User expectedUser = new User("Bo Bobsen","1234",2,1,"b@b.dk",11223344,"adresse 1");
        User actualUser = UserFacade.login("b@b.dk","1234", connectionPool);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void invalidPasswordLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("user","123", connectionPool));
    }

    @Test
    void invalidUserNameLogin() throws DatabaseException
    {
        assertThrows(DatabaseException.class, () -> UserFacade.login("bob","1234", connectionPool));
    }

    @Test
    void createUser() throws DatabaseException
    {
        User newUser = UserFacade.createUser("Mango", "1234", "m@m",666,"adresse 3", connectionPool);
        User logInUser = UserFacade.login("mango","1234", connectionPool);
        User expectedUser = new User("Bo Bobsen","1234",2,1,"b@b.dk",11223344,"adresse 1");
        assertEquals(expectedUser, newUser);
        assertEquals(expectedUser, logInUser);

    }
}