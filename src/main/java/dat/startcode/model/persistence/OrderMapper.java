package dat.startcode.model.persistence;

import dat.startcode.model.dto.StatusDTO;
import dat.startcode.model.dto.UserOrdersDTO;
import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {
    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Order getOrderByOrderId(int orderId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Order order = null;

        String sql = "SELECT * FROM carport.order " +
                "WHERE order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int userId = rs.getInt("user_id");
                    int requestId = rs.getInt("inquiry_id");
                    String drawing = rs.getString("drawing");
                    double costPrice = rs.getDouble("cost_price");
                    double finalPrice = rs.getDouble("final_price");
                    int statusId = rs.getInt("status_id");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    order = new Order(orderId, userId, requestId, drawing, costPrice, finalPrice, statusId, timestamp);
                } else {
                    throw new DatabaseException("Fejl. Kunne ikke finde orderen.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return order;
    }

    public List<UserOrdersDTO> getUserOrderDTOs() throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<UserOrdersDTO> userOrdersDTOList = new ArrayList<>();

        String sql = "SELECT order_id, username, phone_no, inquiry_id , cost_price, final_price, status_id, timestamp FROM carport.order " +
                "INNER JOIN user " +
                "USING(user_id) " +
                "ORDER BY order_id DESC;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int orderId = rs.getInt("order_id");
                    String username = rs.getString("username");
                    int phoneNr = rs.getInt("phone_no");
                    int inquiryId = rs.getInt("inquiry_id");
                    double costPrice = rs.getDouble("cost_price");
                    double finalPrice = rs.getDouble("final_price");
                    int statusId = rs.getInt("status_id");
                    LocalDateTime timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
                    UserOrdersDTO row = new UserOrdersDTO(orderId, username, phoneNr, inquiryId, costPrice, finalPrice, statusId, timestamp);
                    userOrdersDTOList.add(row);
                }
                if (userOrdersDTOList.isEmpty()) {
                    throw new DatabaseException("Fejl. Ingen kunder har lagt nogen ordre?");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return userOrdersDTOList;
    }


    public boolean setOrderStatusByOrderId(int orderId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        boolean result = false;

        String sql = "UPDATE carport.order SET status_id = 2 WHERE carport.order.order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Mere/mindre end 1 row affected da order med id = " + orderId + ". Skulle updates! (check evt. databasen for fejl)");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Kunne ikke opdatere order status for order med id = " + orderId);
        }

        return true;
    }

    public boolean deleteOrderByOrderId(int orderId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        boolean result = false;

        String sql = "delete from carport.order WHERE carport.order.order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Mere/mindre end 1 row affected da order med id = " + orderId + ". Skulle updates! (check evt. databasen for fejl)");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Kunne ikke opdatere order status for order med id = " + orderId);
        }

        return true;
    }

    public int insertOrderIntoDB(int inquiryId, int userId, int status) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Order order = null;
        int newOrderId = 0;

        String sql = "insert into carport.order ( inquiry_id, user_id, status_id, timestamp" +
                " ) values ( ?, ?, ?, NOW())";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, inquiryId);
                ps.setInt(2, userId);
                ps.setInt(3, status);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {

                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newOrderId = idResultset.getInt(1);
                } else {
                    throw new DatabaseException("");
                }
            }
        } catch (SQLException | DatabaseException ex) {
            throw new DatabaseException(ex, "Orderen kunne ikke sættes i databasen");
        }


        return newOrderId;
    }

    public StatusDTO getStatusByUserId(int userId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        StatusDTO statusDTO = null;

        String sql = "SELECT * FROM carport.order " +
                "WHERE user_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int orderId = rs.getInt("order_id");
                    int statusId = rs.getInt("status_id");
                    statusDTO = new StatusDTO(orderId, statusId);
                } else {
                    throw new DatabaseException("Fejl. Kunne ikke finde orderen.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return statusDTO;
    }


    public Order insertEarlyOrderIntoDB(int userID, int inquiryID) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        int newOrderID = 0;
        String sql = "INSERT INTO carport.order (order_id, user_id, inquiry_id," +
                " cost_price, final_price, status_id, timestamp) values (?, ?, ?, ?, ?, ?, NOW())";

        Order earlyOrder = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, 0);
                ps.setInt(2, userID);
                ps.setInt(3, inquiryID);
                ps.setInt(4, 0);
                ps.setInt(5, 0);
                ps.setInt(6, 1);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {

                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newOrderID = idResultset.getInt(1);
                    earlyOrder = new Order(newOrderID, userID, inquiryID, "", 0, 0, 1, null);

                } else {
                    throw new DatabaseException("");
                }
            }
        } catch (SQLException | DatabaseException ex) {
            throw new DatabaseException(ex, "Forespørgsel kunne ikke sættes i databasen");
        }

        return earlyOrder;
    }


    public boolean updateOrderFinalPriceById(int orderId, double price) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        boolean result = false;

        String sql = "UPDATE carport.order SET final_price = ? WHERE carport.order.order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, price);
                ps.setInt(2, orderId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Mere/mindre end 1 row affected da order med id = " + orderId + ". Skulle updates! (check evt. databasen for fejl)");
                }
            }
        } catch (SQLException | DatabaseException ex) {
            throw new DatabaseException(ex, "Orderen kunne ikke sættes i databasen");
        }

        return result;
    }

    public double calcNewCostPrice(int order_id) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        double costPrice = -1;

        String sql = "SELECT SUM(price*quantity) as cost_price FROM carport.materials " +
                "INNER JOIN carport.bills_of_material " +
                "USING(material_id) " +
                "WHERE order_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    costPrice = rs.getDouble("cost_price");
                }
                if (costPrice < 0) {
                    throw new DatabaseException("Fejl. Kunne ikke beregne ny cost_price");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return costPrice;
    }

    public boolean updateOrderCostPriceById(int orderId, double price) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        boolean result = false;

        String sql = "UPDATE carport.order SET cost_price = ? WHERE carport.order.order_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, price);
                ps.setInt(2, orderId);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Mere/mindre end 1 row affected da order med id = " + orderId + ". Skulle updates! (check evt. databasen for fejl)");
                }
            }
        } catch (SQLException | DatabaseException ex) {
            throw new DatabaseException(ex, "Orderen kunne ikke sættes i databasen");
        }

        return result;
    }
}

