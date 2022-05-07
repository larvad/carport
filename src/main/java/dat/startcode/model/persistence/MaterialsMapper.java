
package dat.startcode.model.persistence;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.Materials;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaterialsMapper {
    ConnectionPool connectionPool;

    public MaterialsMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Materials getMaterialsById(int materialId) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        Materials materials = null;

        String sql = "SELECT * FROM carport.materials " +
                "WHERE material_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, materialId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    String type = rs.getString("type");
                    int height = rs.getInt("height");
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    String unit = rs.getString("unit");
                    int categoryId = rs.getInt("category_id");
                    int angle = rs.getInt("angle");
                    int rollLength = rs.getInt("roll_length");
                    int amountInBox = rs.getInt("amount_in_box");
                    double price = rs.getDouble("price");
                    materials = new Materials(materialId, type, height, width, length, unit, categoryId, price);
                } else {
                    throw new DatabaseException("Fejl. Kunne ikke finde materialet.");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return materials;
    }

}

