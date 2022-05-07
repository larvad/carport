package dat.startcode.model.persistence;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderMapper {
    ConnectionPool connectionPool;

    public OrderMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Order getOrderById(int orderId) throws DatabaseException {
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
                    int requestId = rs.getInt("request_id");
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

}

