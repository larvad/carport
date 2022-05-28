package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;
import dat.startcode.model.services.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminApprove extends Command{
    private ConnectionPool connectionPool;

    public AdminApprove() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        Authentication.isRoleAllowed(2, request);

        boolean order;

        int orderId = Integer.parseInt(request.getParameter("approve"));

        String ChangeStatusID = request.getParameter("changeStatus");

        order = Facade.setOrderStatusByOrderId(orderId,2, connectionPool);

        Admin admin = new Admin();

        return admin.execute(request,response);

    }
}
