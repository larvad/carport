package dat.startcode.control;

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

public class AdminEdit2 extends Command {
    private ConnectionPool connectionPool;

    public AdminEdit2() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        //region inquiry
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

        HttpSession session = request.getSession();

        inquiry = (Inquiry) session.getAttribute("inquiry");

        Inquiry brandSpankingNewInquiry = new Inquiry(inquiry.getInquiryId(), carpWidth, carpLength, roofType, roofSlope, shedWidth, shedLength);

        boolean succes = UserFacade.updateInquiryByInquiryId(brandSpankingNewInquiry, connectionPool);

        if (succes)
            session.setAttribute("inquiry", brandSpankingNewInquiry);

        //endregion

        //region opdater final price
        Order order = (Order) session.getAttribute("order");
        double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));

        //TODO: Delete og genbergn stykliste! før costprice updateres...
        double costPrice = UserFacade.updateOrderCostPriceById(order.getOrderId(), connectionPool);
        succes = UserFacade.updateOrderFinalPriceById(order.getOrderId(), finalPrice, connectionPool);
        order = UserFacade.getOrderById(order.getOrderId(), connectionPool); //opdater orderen således at prisen opdateres på refresh
        if (succes)
            session.setAttribute("order", order);
        //endregion

        return "adminEditCarport"; //genindlæs samme side, nu med det nye data
    }
}
