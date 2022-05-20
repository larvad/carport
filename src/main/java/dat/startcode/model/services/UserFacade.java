package dat.startcode.model.services;

import dat.startcode.model.dto.StatusDTO;
import dat.startcode.model.dto.BomDTO;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Materials;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.dto.BomDTO;
import dat.startcode.model.entities.*;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.*;
import dat.startcode.model.dto.UserOrdersDTO;

import java.util.List;

public class UserFacade {
    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException {
        UserMapper userMapper = new UserMapper(connectionPool);
        return userMapper.login(email, password);
    }

    public static User createUser(String username, String email, String password, int phoneNr, String adresse, ConnectionPool connectionPool) throws DatabaseException {
        UserMapper userMapper = new UserMapper(connectionPool);
        return userMapper.createUser(username, email, password, phoneNr, adresse);
    }

    public static List<Materials> showFlatRoofMaterial(ConnectionPool connectionPool) throws DatabaseException{
        MaterialsMapper materialsMapper = new MaterialsMapper(connectionPool);
        return materialsMapper.ShowFlatRoofMaterials();
    }

    public static List<Materials> showRaisedRoofMaterial(ConnectionPool connectionPool) throws DatabaseException{
        MaterialsMapper materialsMapper = new MaterialsMapper(connectionPool);
        return materialsMapper.ShowRaisedRoofMaterials();
    }

    public static Inquiry insertInquiryIntoDB(int carpWidth, int carpLength, String roofType, int roofSlope, int shedWidth, int shedLength, ConnectionPool connectionPool) throws DatabaseException {
        RequestMapper requestMapper = new RequestMapper(connectionPool);
        return requestMapper.insertInquiryIntoDB(carpWidth, carpLength, roofType, roofSlope, shedWidth, shedLength);
    }

    public static List<UserOrdersDTO> getAdminPageTableData(ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.getUserOrderDTOs();
    }

    public static boolean setOrderStatusByOrderId(int orderId ,ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.setOrderStatusByOrderId(orderId);
    }

    public static boolean deleteOrderByOrderId(int orderId,ConnectionPool connectionPool) throws DatabaseException{
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.deleteOrderByOrderId(orderId);
    }

    public static int insertOrderIntoDB(int inquiryId, int userId, int status, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.insertOrderIntoDB(inquiryId,userId,status);
    }

    public static boolean updateInquiryByInquiryId(Inquiry inquiry, ConnectionPool connectionPool) throws DatabaseException{
        RequestMapper requestMapper = new RequestMapper(connectionPool);
        return requestMapper.updateInquiryByInquiryId(inquiry);
    }

    public static Order getOrderById(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.getOrderByOrderId(orderId);
    }

    public static Inquiry getRequestById(int inquiryId, ConnectionPool connectionPool) throws DatabaseException {
        RequestMapper requestMapper = new RequestMapper(connectionPool);
        return requestMapper.getRequestById(inquiryId);
    }


    public static boolean updateOrderFinalPriceById(int orderId, double price, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.updateOrderFinalPriceById(orderId, price);
    }

    public static StatusDTO getStatusDTOByUserID (int userID, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        return orderMapper.getStatusByUserId(userID);
    }

    public static List<BomDTO> showBOMTraeOgTagplader(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        BillsOfMaterialMapper billsOfMaterialMapper = new BillsOfMaterialMapper(connectionPool);
        return billsOfMaterialMapper.showBOMTraeOgTagplader(orderId);
    }

    public static List<BomDTO> showBOMSkruerOgBeslag(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        BillsOfMaterialMapper billsOfMaterialMapper = new BillsOfMaterialMapper(connectionPool);
        return billsOfMaterialMapper.showBOMSkruerOgBeslag(orderId);
    }

    public static boolean deleteBoMbyID(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        BillsOfMaterialMapper billsOfMaterialMapper = new BillsOfMaterialMapper(connectionPool);
        return billsOfMaterialMapper.deleteBoMbyId(orderId);
    }

    public static double updateOrderCostPriceById(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper orderMapper = new OrderMapper(connectionPool);
        double costPrice = orderMapper.calcNewCostPrice(orderId);
        boolean success = orderMapper.updateOrderCostPriceById(orderId, costPrice);
        return costPrice;
    }
}

//TODO: lave UserFacade om til Facade, og rykke den op i persistence mappen. Lade alle vores klasser i control køre deres metoder over facaden.
