package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dto.UserOrdersDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Admin extends Command {

    private ConnectionPool connectionPool;

    public Admin() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        List<UserOrdersDTO> userOrdersDTOList = Facade.getAdminPageTableData(connectionPool);
        request.setAttribute("userOrdersDTOList", userOrdersDTOList);

        return "admin";
    }
}
