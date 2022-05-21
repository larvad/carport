package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dto.StatusDTO;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Profile extends Command{

    private ConnectionPool connectionPool;
    public Profile() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();


        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        StatusDTO statusDTO = UserFacade.getStatusDTOByUserID(userId, connectionPool);
        if (request.getParameter("statusDTO") == null) {

            session.setAttribute("statusDTO", statusDTO);
        }

        Order order = UserFacade.getOrderById(statusDTO.getOrderID(), connectionPool);
        session.setAttribute("finalPrice", (int) order.getFinalPrice());
        session.setAttribute("requestID", order.getRequestId());
        return "profile";
    }
}
