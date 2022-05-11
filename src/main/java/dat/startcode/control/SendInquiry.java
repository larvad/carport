package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.CustomerRequest;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendInquiry extends Command {
    private ConnectionPool connectionPool;

    public SendInquiry() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        CustomerRequest customerRequest = null;
        int carpWidth;
        int carpLength;
        String roofTypeFlat;
        String roofTypeRaised;
        int roofSlope;
        int shedWidth;
        int shedLength;

        try {
            String [] ShermanFireFly = request.getParameterValues("inlineRadioOptions");
            String T34 = request.getParameter("radio7");
            carpWidth = Integer.parseInt(request.getParameter("carpWidth"));
            carpLength = Integer.parseInt(request.getParameter("carpLength"));
            roofTypeFlat = request.getParameter("roofType");
            roofTypeRaised = request.getParameter("roofType");
            roofSlope = Integer.parseInt(request.getParameter("roofSloop"));
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }



        return "createCarport";    //placeholder
    }
}
