package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
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

        boolean order;
        String mode = request.getParameter("CRUD"); // henter valgt knap
        int orderId = Integer.parseInt(request.getParameter("orderSelect"));

        switch(mode){
            case "godkend":
                order = UserFacade.setOrderStatusByOrderId(orderId, connectionPool);
                break;
            case "slet":
                order = UserFacade.deleteOrderByOrderId(orderId, connectionPool);
                break;
            case "redigere":
                order = UserFacade.updateOrderByOrderId(orderId,connectionPool);
        }

        Admin admin = new Admin();

        return admin.execute(request,response);
    }
}
