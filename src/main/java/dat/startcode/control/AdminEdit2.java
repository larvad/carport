package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.Facade;
import dat.startcode.model.services.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AdminEdit2 extends Command {
    private ConnectionPool connectionPool;

    public AdminEdit2() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        Authentication.isRoleAllowed(2, request);

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

        inquiry = new Inquiry(inquiry.getInquiryId(), carpWidth, carpLength, roofType, roofSlope, shedWidth, shedLength);

        boolean succes = Facade.updateInquiryByInquiryId(inquiry, connectionPool);

        if (succes)
            session.setAttribute("inquiry", inquiry);

        //endregion

        //region opdater cost- og finalprice
        Order order = (Order) session.getAttribute("order");
        double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));

        //Delete og genbergn stykliste! ellers opdateres costPrice ikke
        Facade.deleteBoMbyID(order.getOrderId(), connectionPool);
        Facade.calculate(order.getOrderId(), inquiry, connectionPool);

        //Nu hvor styklisten er opdateret kan der regnes på costPrice
        double costPrice = Facade.calcOrderCostPriceById(order.getOrderId(), connectionPool);
        BigDecimal bdCostPrice = new BigDecimal(costPrice).setScale(2, RoundingMode.HALF_UP);
        costPrice = bdCostPrice.doubleValue();
        Facade.updateOrderCostPriceById(order.getOrderId(), costPrice, connectionPool);

        succes = Facade.updateOrderFinalPriceById(order.getOrderId(), finalPrice, connectionPool);
        order = Facade.getOrderById(order.getOrderId(), connectionPool); //opdater orderen således at prisen opdateres på refresh
        if (succes)
            session.setAttribute("order", order);
        //endregion

        return "adminEditCarport"; //genindlæs samme side, nu med det nye data
    }
}
