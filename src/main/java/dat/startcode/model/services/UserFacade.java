package dat.startcode.model.services;

import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Materials;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.MaterialsMapper;
import dat.startcode.model.persistence.RequestMapper;
import dat.startcode.model.persistence.UserMapper;
import dat.startcode.model.dto.UserOrdersDTO;
import dat.startcode.model.persistence.OrderMapper;
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
}

//TODO: lave UserFacade om til Facade, og rykke den op i persistence mappen. Lade alle vores klasser i control køre deres metoder over facaden.
