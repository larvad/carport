package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class AdminDelete extends Command{
    private ConnectionPool connectionPool;

    public AdminDelete() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        boolean succes;

        int orderId = Integer.parseInt(request.getParameter("delete"));
        succes = UserFacade.deleteOrderByOrderId(orderId, connectionPool);

        Admin admin = new Admin();
        return admin.execute(request,response);
    }
}
