package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Inquiry;
import dat.startcode.model.entities.Order;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.SVG;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSVG extends Command{

    private ConnectionPool connectionPool;


    public ShowSVG() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        int orderId = Integer.parseInt(request.getParameter("svg"));
        Order order = UserFacade.getOrderById(orderId, connectionPool);
        Inquiry inquiry = UserFacade.getRequestById(order.getRequestId(), connectionPool);
        int carpWidth = inquiry.getCarpWidth()/10;
        int carpLength = inquiry.getCarpLength()/10;

        SVG svg = new SVG(0, 0, "0 0 600 840", 100, 100 );

        // tegn carport bredde og længde
        svg.addRect(0, 0, carpWidth, carpLength);

        //tegn remme

        // tegn spær
        for (int x = 0; x < carpLength/50; x++)
        {
            svg.addRect(10 + 50 * x, 0, carpLength, 4.5);
        }

        // tegn kryds
        svg.addStripedLine(0,0,carpLength, carpWidth);

        request.setAttribute("svg", svg.toString());
        return "svg";
    }
}
