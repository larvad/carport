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

public class AdminCRUD extends Command {
    private ConnectionPool connectionPool;

    public AdminCRUD() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        Authentication.isRoleAllowed(2, request);

        boolean succes;
        Inquiry inquiry = null;

        String mode = request.getParameter("CRUD"); // henter valgt knap
        int orderId = Integer.parseInt(request.getParameter("orderSelect"));

        switch(mode){
            case "godkend":
                succes = Facade.setOrderStatusByOrderId(orderId,2, connectionPool);
                break;
            case "slet":
                succes = Facade.deleteOrderByOrderId(orderId, connectionPool);
                break;
            case "rediger":
                Order order = Facade.getOrderById(orderId, connectionPool);
                inquiry = Facade.getRequestById(order.getRequestId(), connectionPool);
                request.setAttribute("inquiry", inquiry);
                return "adminEditCarport";
            case "opdater":
                succes = Facade.updateInquiryByInquiryId(inquiry,connectionPool);

        }

        Admin admin = new Admin();

        return admin.execute(request,response);
    }
}
