package dat.startcode.model.persistence;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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


    public List<BillsOfMaterial> insertBOMList(List<BillsOfMaterial> billsOfMaterialList) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");
        List<BillsOfMaterial> billsOfMaterialListWithID = new ArrayList<>();

        int bomId = 0;
        int materialId = 0;
        int orderId = 0;
        int quantity = 0;
        String description = "";

        for (BillsOfMaterial billsOfMaterial : billsOfMaterialList) {
            bomId = billsOfMaterial.getBomId();
            materialId = billsOfMaterial.getMaterialId();
            orderId = billsOfMaterial.getOrderId();
            quantity = billsOfMaterial.getQuantity();
            description = billsOfMaterial.getDescription();


            BillsOfMaterial billsOfMaterialWithID = null;
            int newId = 0;
            String sql = "INSERT INTO carport.bills_of_material (bom_id, material_id, order_id, quantity, description) values (?,?,?,?,?)";
            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setInt(1, bomId);
                    ps.setInt(2, materialId);
                    ps.setInt(3, orderId);
                    ps.setInt(4, quantity);
                    ps.setString(5, description);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected == 1) {
                        ResultSet idResultset = ps.getGeneratedKeys();
                        if (idResultset.next()) {
                            newId = idResultset.getInt(1);
                            billsOfMaterialWithID = new BillsOfMaterial(newId, materialId, orderId, quantity, description);
                            billsOfMaterialListWithID.add(billsOfMaterialWithID);
                        }
                    } else {
                        throw new DatabaseException("The BOM with BOMID = " + newId + " could not be inserted into the database");
                    }
                }
            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Could not insert BOM into database");
            }
        }
        return billsOfMaterialListWithID;
    }

}
