package dat.startcode.control;

import com.mysql.cj.Session;
import dat.startcode.logic.RequestCalculator;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Order;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.sql.Timestamp;

public class SendInquiry extends Command {
    private ConnectionPool connectionPool;

    public SendInquiry() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Inquiry inquiry = null;
        int carpWidth;
        int carpLength;
        String roofType;
        int roofSlope;
        int shedWidth;
        int shedLength;
        String checkboxShed;


        try {
            String M18Hellcat = request.getParameter("inlineRadioOptions");
            carpWidth = Integer.parseInt(request.getParameter("carpWidth"));
            carpLength = Integer.parseInt(request.getParameter("carpLength"));
            roofType = request.getParameter("roofType");
            try {
                roofSlope = Integer.parseInt(request.getParameter("roofSloop"));
            } catch (Exception e) {
                roofSlope = 0;
            }
            checkboxShed = (request.getParameter("checkboxShed"));
            if (checkboxShed != null) {
                shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
                shedLength = Integer.parseInt(request.getParameter("shedLength"));
            } else {
                shedWidth = 0;
                shedLength = 0;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        inquiry = UserFacade.insertInquiryIntoDB(carpWidth, carpLength, roofType, roofSlope, shedWidth, shedLength, connectionPool);
        //Generér OderId
        int orderId = UserFacade.insertOrderIntoDB(inquiry.getInquiryId(), user.getUserId(), 1, connectionPool);

        request.setAttribute("inquiry", inquiry);

        //Generér OderId

        int userId = user.getUserId();
        int inquiryID = inquiry.getInquiryId();

        //Få orderIDet ind i RequestCalculator + kald beregning, som laver BOM
        RequestCalculator requestCalculator = new RequestCalculator();
        requestCalculator.calculate(orderId, inquiry, connectionPool);

        //Udregn og tilføj cost_price til databasen
        double costPrice = UserFacade.updateOrderCostPriceById(orderId, connectionPool);
        UserFacade.updateOrderFinalPriceById(orderId, costPrice * 1.3, connectionPool);

        return "confirmInquiry";        //TODO: nice to have: sætte nogle krav til skur mål
    }
}
