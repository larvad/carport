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

        int orderId = Integer.parseInt(request.getParameter("drawing"));
        Order order = UserFacade.getOrderById(orderId, connectionPool);
        Inquiry inquiry = UserFacade.getRequestById(order.getRequestId(), connectionPool);
        int carpWidth = inquiry.getCarpWidth()/10;
        int carpLength = inquiry.getCarpLength()/10;
        int shedWidth = inquiry.getShedWidth()/10;
        int shedLength = inquiry.getShedLength()/10;
        double rafterThickness = 4.5;
        double remThickness = 4.5;
        double columnThickness = 9.7;

        SVG svg2 = new SVG(100, 100, "0 0 "+ carpWidth + " " + carpLength, 800, 640 );
        SVG svg1 = new SVG(0, 0, "0 0 "+ carpWidth + " " + carpLength, 1000, 1000 );

        drawCarportRoof(svg2, carpWidth, carpLength);
        drawShed(svg2, carpWidth, carpLength, shedWidth, shedLength);
        drawRafters(svg2,carpWidth, carpLength, rafterThickness);
        drawRems(svg2, carpWidth, carpLength, rafterThickness);
        drawCross(svg2, carpWidth, carpLength, remThickness);
        drawColumnWithoutShed(svg2, carpWidth, carpLength, columnThickness);
        svg1.addSvg(drawArrows(svg2, carpWidth, carpLength));

        request.setAttribute("svg", svg1.toString());
        return "svg";
    }


    public void drawCarportRoof(SVG svg, int carpWidth, int carpLength){
        svg.addRect(0, 0, carpWidth, carpLength);
    }

    private void drawShed(SVG svg, int carpWidth, int carpLength, int shedWidth, int shedLength) {
        if (shedLength > 0){
            svg.addRectBold(carpLength-30-shedLength, 35, shedWidth, shedLength);
        }
    }

    // TODO: få antal spær fra styklisten og divider carpLength med det for at få afstand
    public void drawRafters(SVG svg, int carpWidth, int carpLength, double remThickness){
        for (int x = 0; x < carpLength/50; x++) {
            svg.addRect(50 + 50 * x, 0, carpWidth, remThickness);
        }
    }

    public void drawRems (SVG svg, int carpWidth, int carpLength, double rafterThickness){
        svg.addRect(0, 35, rafterThickness, carpLength);
        svg.addRect(0, carpWidth-35, rafterThickness, carpLength);
    }

    public void drawCross (SVG svg, int carpWidth, int carpLength, double remThickness){
        svg.addStripedLine(50,35+remThickness, carpLength-50, carpWidth-35);
        svg.addStripedLine(50, carpWidth-35, carpLength-50, 35+remThickness);
    }

    public void drawColumnWithoutShed(SVG svg, int carpWidth, int carpLength, double columnThickness){
        int frontDistance = 0;

        if (carpLength < 300){
            frontDistance = 50;
            svg.addRect(frontDistance-2.5, 35-2.5, columnThickness, columnThickness);
            svg.addRect(carpLength-50-2.5, 35-2.5, columnThickness, columnThickness);
            svg.addRect(frontDistance-2.5, carpWidth-35-2.5, columnThickness, columnThickness);
            svg.addRect(carpLength-50-2.5, carpWidth-35-2.5, columnThickness, columnThickness);
        }else {
            frontDistance = 100;
            svg.addRect(frontDistance - 2.5, 35 - 2.5, 9.7, 9.7);
            svg.addRect(carpLength - 50 - 2.5, 35 - 2.5, 9.7, 9.7);
            svg.addRect(frontDistance - 2.5, carpWidth - 35 - 2.5, 9.7, 9.7);
            svg.addRect(carpLength - 50 - 2.5, carpWidth - 35 - 2.5, 9.7, 9.7);
        }
        if (carpLength > 455){
            int x = (carpLength-100-50)/2;
            svg.addRect(x+100, 35-2.5, 9.7, 9.7);
            svg.addRect(x+100, carpWidth-35-2.5, 9.7, 9.7);
        }

    }

    public void drawColumnWithShed(SVG svg, int carpWidth, int carpLength, double columnThickness) {
        int frontDistance = 0;

        if (carpLength < 300) {
            frontDistance = 50;
            svg.addRect(frontDistance - 2.5, 35 - 2.5, columnThickness, columnThickness);
            svg.addRect(frontDistance - 2.5, carpWidth - 35 - 2.5, columnThickness, columnThickness);
        } else {
            frontDistance = 100;
            svg.addRect(frontDistance - 2.5, 35 - 2.5, 9.7, 9.7);
            svg.addRect(frontDistance - 2.5, carpWidth - 35 - 2.5, 9.7, 9.7);
        }
    }

    private SVG drawArrows(SVG svg, int carpWidth, int carpLength) {
        SVG svg3 = new SVG(0, 0, "0 0 "+ carpWidth + " " + carpLength, 1000, 1000 );
        svg3.addRect(0,0,1000, 1000);
        svg3.addArrows(0,0, 0, carpWidth);
        return svg3;
    }



}
