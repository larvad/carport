package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.OrderMapper;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminCRUD extends Command {
    private ConnectionPool connectionPool;

    public AdminCRUD() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        boolean succes;

        String mode = request.getParameter("CRUD"); // henter valgt knap
        int orderId = Integer.parseInt(request.getParameter("orderSelect"));

        switch(mode){
            case "godkend":
                succes = UserFacade.setOrderStatusByOrderId(orderId, connectionPool);
                break;
            case "slet":
                succes = UserFacade.deleteOrderByOrderId(orderId, connectionPool);
                break;
            case "rediger":
                Order order = UserFacade.getOrderById(orderId, connectionPool);
                Inquiry inquiry = UserFacade.getRequestById(order.getRequestId(), connectionPool);
                //succes = UserFacade.updateOrderByOrderId(orderId,connectionPool);
                return "adminEditCarport";
        }

        Admin admin = new Admin();

        return admin.execute(request,response);
    }
}
