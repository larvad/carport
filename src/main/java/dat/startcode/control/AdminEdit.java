package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;
import dat.startcode.model.services.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminEdit extends Command {
    private ConnectionPool connectionPool;

    public AdminEdit() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        Authentication.isRoleAllowed(2, request);

        int orderId = Integer.parseInt(request.getParameter("edit")); // henter orderId fra button value

        Order order = Facade.getOrderById(orderId, connectionPool);
        Inquiry inquiry = Facade.getInquiryById(order.getRequestId(), connectionPool);

        session.setAttribute("inquiry", inquiry);
        session.setAttribute("order", order);

        return "adminEditCarport";
    }
}
