package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dto.UserOrdersDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminDeleteOrder extends Command {

    private ConnectionPool connectionPool;

    public AdminDeleteOrder() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {


        List<UserOrdersDTO> userOrdersDTOList = UserFacade.getAdminPageTableData(connectionPool);
        //TODO: hent en DTO fra databasen til visning i tabel på admin.jsp
        request.setAttribute("userOrdersDTOList", userOrdersDTOList);

        return "admin";
    }
}

