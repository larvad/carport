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

        SVG svg = new SVG(0, 0, "0 0 "+ carpWidth + " " + carpLength, 800, 640 );

        // tegn carport bredde og længde
        svg.addRect(0, 0, carpWidth, carpLength);

        // tegn spær
        for (int x = 0; x < carpLength/50; x++) {
            svg.addRect(50 + 50 * x, 0, carpWidth, 4.5);
        }

        //tegn remme
        svg.addRect(0, 35, 4.5, carpLength);
        svg.addRect(0, carpWidth-35, 4.5, carpLength);

        // tegn kryds
        svg.addStripedLine(50,35+4.5, carpLength-50, carpWidth-35);
        svg.addStripedLine(50, carpWidth-35, carpLength-50, 35+4.5);

        // tegn stolper
        if (carpLength < 300){
            svg.addRect(50-2.5, 35-2.5, 9.7, 9.7);
            svg.addRect(carpLength-50-2.5, 35-2.5, 9.7, 9.7);
            svg.addRect(50-2.5, carpWidth-35-2.5, 9.7, 9.7);
            svg.addRect(carpLength-50-2.5, carpWidth-35-2.5, 9.7, 9.7);
        }else {

            svg.addRect(100 - 2.5, 35 - 2.5, 9.7, 9.7);
            svg.addRect(carpLength - 50 - 2.5, 35 - 2.5, 9.7, 9.7);
            svg.addRect(100 - 2.5, carpWidth - 35 - 2.5, 9.7, 9.7);
            svg.addRect(carpLength - 50 - 2.5, carpWidth - 35 - 2.5, 9.7, 9.7);
        }

        if (carpLength > 455){
            int x = (carpLength-100-50)/2;
            svg.addRect(x+100, 35-2.5, 9.7, 9.7);
            svg.addRect(x+100, carpWidth-35-2.5, 9.7, 9.7);
        }


        request.setAttribute("svg", svg.toString());
        return "svg";
    }
}
