package dat.startcode.model.persistence;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.Request;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestMapper {
    ConnectionPool connectionPool;

    public RequestMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Request getRequestById(int requestId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Request request = null;

        String sql = "SELECT * FROM carport.reques " +
                "WHERE request_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, requestId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int carpWidth = rs.getInt("carp_width");
                    int carpLength = rs.getInt("carp_length");
                    String roofType = rs.getString("roof_type");
                    int roofSlope = rs.getInt("roof_slope");
                    int shedWith = rs.getInt("shed_width");
                    int shedLength = rs.getInt("shed_length");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    request = new Request(requestId, carpWidth, carpLength, roofType, roofSlope, shedWith, shedLength, timestamp);
                } else {
                    throw new DatabaseException("Fejl. Kunne ikke finde forespørgslen.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return request;
    }

}


