package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper implements IUserMapper {
    ConnectionPool connectionPool;

    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public User login(String email, String password) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        User user = null;

        String sql = "SELECT * FROM user " +
                "WHERE email = ? AND password = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int userId = rs.getInt("user_id");
                    String username = rs.getString("username");
                    int roleId = rs.getInt("role_id");
                    int phoneNumber = rs.getInt("phone_no");
                    String adresse = rs.getString("adresse");
                    user = new User(username, password, roleId, userId, email, phoneNumber, adresse);
                } else {
                    throw new DatabaseException("Wrong username or password");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error logging in. Something went wrong with the database");
        }
        return user;
    }

    @Override
    public User createUser(String username, String email, String password, int phoneNr, String adresse) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        User user = null;
        int newId = 0;
        String sql = "insert into user (username, email, password, role_id, phone_no, adresse) values (?,?,?,?,?,?)";
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);
                ps.setInt(4, 1);
                ps.setInt(5, phoneNr);
                ps.setString(6, adresse);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    ResultSet idResultset = ps.getGeneratedKeys();
                    if (idResultset.next()) {
                        newId = idResultset.getInt(1);
                        user = new User(username, password, 1, newId, email, phoneNr, adresse);
                    }
                } else {
                    throw new DatabaseException("The user with username = " + username + " could not be inserted into the database");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert user into database");
        }
        return user;
    }


}
