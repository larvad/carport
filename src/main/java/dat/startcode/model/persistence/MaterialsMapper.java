package dat.startcode.model.persistence;

import dat.startcode.model.entities.BillsOfMaterial;
import dat.startcode.model.entities.Materials;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public List<Materials> ShowFlatRoofMaterials() throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");

        List<Materials> getFlatRoofMaterials = new ArrayList<>();

        String sql = "SELECT  distinct type " +
                "FROM materials " +
                "where carport.materials.category_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1,3);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    String type = rs.getString("type");

                    Materials materials = new Materials(type);
                    getFlatRoofMaterials.add(materials);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getFlatRoofMaterials;
    }

    public List<Materials> ShowRaisedRoofMaterials() throws DatabaseException{
        Logger.getLogger("web").log(Level.INFO, "");

        List<Materials> getRaisedRoofMaterials = new ArrayList<>();

        String sql = "SELECT  distinct type " +
                "FROM materials " +
                "where carport.materials.category_id = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setInt(1,4);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    String type = rs.getString("type");

                    Materials materials = new Materials(type);
                    getRaisedRoofMaterials.add(materials);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getRaisedRoofMaterials;
    }

    public List<Materials> getMaterialsByType(String type) throws DatabaseException {
        Logger.getLogger("web").log(Level.INFO, "");

        List<Materials> materialsList = new ArrayList<>();
        Materials materials = null;

        String sql = "SELECT * FROM carport.materials " +
                "WHERE type = ? ORDER BY length ASC";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, type);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {

                    int materialId = rs.getInt("material_id");
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
                    materialsList.add(materials);
                }
                if (materialsList.isEmpty()) {
                    throw new DatabaseException("Fejl. Materialet var ikke i databasen?");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Something went wrong with the database");
        }
        return materialsList;
    }

}

