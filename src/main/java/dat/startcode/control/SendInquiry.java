package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
                int divideBy = Integer.parseInt(request.getParameter("shedWidth"));
                shedWidth = (carpWidth - 600) / divideBy;
                shedLength = Integer.parseInt(request.getParameter("shedLength"));
                if (shedLength >= carpLength)
                {
                    request.setAttribute("shed40", "Skur længde må ikke være længere end carporten!");
                    return "createCarport";
                }
            } else {
                shedWidth = 0;
                shedLength = 0;
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        inquiry = UserFacade.insertInquiryIntoDB(carpWidth, carpLength, roofType, roofSlope, shedWidth, shedLength, connectionPool);

        //Generér OderId
        int userId = user.getUserId();
        int inquiryID = inquiry.getInquiryId();
        int orderId = UserFacade.insertOrderIntoDB(inquiryID, userId,1, connectionPool);

        request.setAttribute("inquiry", inquiry);

        //Få orderIDet ind i RequestCalculator + kald beregning, som laver BOM
        UserFacade.calculate(orderId, inquiry, connectionPool);

        //Udregn costPrice og afrund til 2 decimaler
        double costPrice = UserFacade.calcOrderCostPriceById(orderId, connectionPool);
        BigDecimal bdCostPrice = new BigDecimal(costPrice).setScale(2, RoundingMode.HALF_UP);
        costPrice = bdCostPrice.doubleValue();

        //Tilføj cost_price til databasen
        UserFacade.updateOrderCostPriceById(orderId, costPrice, connectionPool);

        //Afrund finalPrice til 2 decimaler (finalPrice er costprice*1,3)
        BigDecimal bdFinalPrice = new BigDecimal(costPrice * 1.3).setScale(2, RoundingMode.HALF_UP);

        //Omdan BigDecimal tilbage til double
        double finalPrice = bdFinalPrice.doubleValue();

        //Tilføj finalPrice til databasen
        UserFacade.updateOrderFinalPriceById(orderId, finalPrice, connectionPool);

        return "confirmInquiry";
    }
}
