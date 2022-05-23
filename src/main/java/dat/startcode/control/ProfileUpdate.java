package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dto.StatusDTO;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileUpdate extends Command {

    private ConnectionPool connectionPool;

    public ProfileUpdate() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();


        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        StatusDTO statusDTO = Facade.getStatusDTOByUserID(userId, connectionPool);
        Facade.setOrderStatusByOrderId2(statusDTO.getOrderID(), connectionPool);
        statusDTO = Facade.getStatusDTOByUserID(userId, connectionPool);
        session.setAttribute("statusDTO", statusDTO);


        return "completingOrder";
    }
}
