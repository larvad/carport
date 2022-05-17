package dat.startcode.model.persistence;

import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestMapper {
    ConnectionPool connectionPool;

    public RequestMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Inquiry getRequestById(int inquiryId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Inquiry inquiry = null;

        String sql = "SELECT * FROM carport.inquiry " +
                "WHERE inquiry_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, inquiryId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int carpWidth = rs.getInt("carp_width");
                    int carpLength = rs.getInt("carp_length");
                    String roofType = rs.getString("roof_type");
                    int roofSlope = rs.getInt("roof_slope");
                    int shedWith = rs.getInt("shed_width");
                    int shedLength = rs.getInt("shed_length");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    inquiry = new Inquiry(inquiryId, carpWidth, carpLength, roofType, roofSlope, shedWith, shedLength, timestamp);
                } else {
                    throw new DatabaseException("Fejl. Kunne ikke finde forespørgslen.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return inquiry;
    }

    public Inquiry insertInquiryIntoDB(int carpWidth, int carpLength, String roofType,
                                       int roofSlope, int shedWidth, int shedLength) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        int newInquiryID = 0;
        String sql = "insert into carport.inquiry ( carp_width, carp_length, roof_type," +
                " roof_slope, shed_width, shed_length, timestamp) values (?, ?, ?, ?, ?, ?, NOW())";

        Inquiry inquiry = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, carpWidth);
                ps.setInt(2, carpLength);
                ps.setString(3, roofType);
                ps.setInt(4, roofSlope);
                ps.setInt(5, shedWidth);
                ps.setInt(6, shedLength);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {

                }
                ResultSet idResultset = ps.getGeneratedKeys();
                if (idResultset.next()) {
                    newInquiryID = idResultset.getInt(1);
                    inquiry = new Inquiry(newInquiryID, carpWidth, carpLength,
                            roofType, roofSlope, shedWidth, shedLength);
                } else {
                    throw new DatabaseException("");
                }
            }
        } catch (SQLException | DatabaseException ex) {
            throw new DatabaseException(ex, "Forespørgsel kunne ikke sættes i databasen");
        }

        return inquiry;
    }


    public boolean updateInquiryByInquiryId(Inquiry inquiry) throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");

        boolean result = false;

        String sql = "UPDATE carport.inquiry SET carp_width = ?, carp_length = ?, roof_type = ?, " +
                "roof_slope = ?, shed_width = ?, shed_length = ? WHERE carport.inquiry.inquiry_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, inquiry.getCarpWidth());
                ps.setInt(2, inquiry.getCarpLength());
                ps.setString(3, inquiry.getRoofType());
                ps.setInt(4, inquiry.getRoofSlope());
                ps.setInt(5, inquiry.getShedWidth());
                ps.setInt(6, inquiry.getShedLength());
                ps.setInt(7, inquiry.getInquiryId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                } else {
                    throw new DatabaseException("Forespørgsel kunne ikke opdateres");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Kunne ikke opdatere forespørgsel med id: " + inquiry.getInquiryId());
        }

        return result;
    }
}


