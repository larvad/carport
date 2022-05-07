package dat.startcode.model.persistence;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillsOfMaterialMapper {
    ConnectionPool connectionPool;

    public BillsOfMaterialMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public BillsOfMaterial getBillsOfMaterialById(int bomId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        BillsOfMaterial billsOfMaterial = null;

        String sql = "SELECT * FROM carport.bills_of_material " +
                "WHERE bom_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bomId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    int materialId = rs.getInt("material_id");
                    int orderId = rs.getInt("order_id");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    billsOfMaterial = new BillsOfMaterial(bomId, materialId, orderId, quantity, description);
                } else {
                    throw new DatabaseException("Fejl. Kunne ikke finde styklisten.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return billsOfMaterial;
    }

}
