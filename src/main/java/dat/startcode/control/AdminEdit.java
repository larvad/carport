package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

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

        int inquiryId = Integer.parseInt(request.getParameter("edit")); // henter inquiryId fra button value
        Inquiry inquiry = UserFacade.getRequestById(inquiryId, connectionPool);
        session.setAttribute("inquiry", inquiry);
        return "adminEditCarport";
    }
}
