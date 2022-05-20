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

    private final double RAFTERTHICKNESS = 4.5;
    private final double REMTHICKNESS = 4.5;
    private final double COLUMNTHICKNESS = 9.7;
    private final int ROOFSIDEDISTANCE = 30;
    private final int ROOFBACKDISTANCE = 50;
    private int roofFrontDistance = 100;
    private final int ROOFFRONTDISTANCE1 = 50;
    private final int ROOFFRONTDISTANCE2 = 100;

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

        // SVG svg1 = new SVG(0, 0, "0 0 "+ carpWidth + " " + carpLength, 1000, 1000 );
        SVG svg1 = new SVG(0, 0, "0 0 1000 1000", 1000, 1000 );
        SVG svg2 = new SVG(100, 100, "0 0 "+ carpLength + " " + carpWidth, carpLength, carpWidth );


        drawCarportRoof(svg2, carpWidth, carpLength);
        drawShed(svg2, carpWidth, carpLength, shedWidth, shedLength);
        drawRafters(svg2,carpWidth, carpLength);
        drawRems(svg2, carpWidth, carpLength);
        drawCross(svg2, carpWidth, carpLength);
        drawColumnWithoutShed(svg2, carpWidth, carpLength);
        drawArrows(svg1, carpWidth, carpLength);
        svg1.addSvg(svg2);

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
    public void drawRafters(SVG svg, int carpWidth, int carpLength){
        for (int x = 0; x < carpLength/50; x++) {
            svg.addRect(50 + 50 * x, 0, carpWidth, RAFTERTHICKNESS);
        }
    }

    public void drawRems (SVG svg, int carpWidth, int carpLength){
        svg.addRect(0, ROOFSIDEDISTANCE, REMTHICKNESS, carpLength);
        svg.addRect(0, carpWidth-ROOFSIDEDISTANCE, REMTHICKNESS, carpLength);
    }

    public void drawCross (SVG svg, int carpWidth, int carpLength){
        svg.addStripedLine(50,ROOFSIDEDISTANCE + REMTHICKNESS, carpLength-50, carpWidth-ROOFSIDEDISTANCE);
        svg.addStripedLine(50, carpWidth-ROOFSIDEDISTANCE, carpLength-50, ROOFSIDEDISTANCE + REMTHICKNESS);
    }

    public void drawColumnWithoutShed(SVG svg, int carpWidth, int carpLength){
        if (carpLength < 300){
            svg.addRect(ROOFFRONTDISTANCE1-2.5, ROOFSIDEDISTANCE-2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
            svg.addRect(carpLength-ROOFBACKDISTANCE-2.5, ROOFSIDEDISTANCE-2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
            svg.addRect(ROOFFRONTDISTANCE1-2.5, carpWidth-ROOFSIDEDISTANCE-2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
            svg.addRect(carpLength-ROOFBACKDISTANCE-2.5, carpWidth-ROOFSIDEDISTANCE-2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
        }else {
            svg.addRect(ROOFFRONTDISTANCE2 - 2.5, ROOFSIDEDISTANCE - 2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
            svg.addRect(carpLength - ROOFBACKDISTANCE - 2.5, ROOFSIDEDISTANCE - 2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
            svg.addRect(ROOFFRONTDISTANCE2 - 2.5, carpWidth - ROOFSIDEDISTANCE - 2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
            svg.addRect(carpLength - ROOFBACKDISTANCE - 2.5, carpWidth - ROOFSIDEDISTANCE - 2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
        }
        if (carpLength > 455){
            int x = (carpLength-ROOFFRONTDISTANCE2-ROOFBACKDISTANCE)/2;
            svg.addRect(x + ROOFFRONTDISTANCE2, ROOFSIDEDISTANCE-2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
            svg.addRect(x + ROOFFRONTDISTANCE2, carpWidth-ROOFSIDEDISTANCE-2.5, COLUMNTHICKNESS, COLUMNTHICKNESS);
        }

    }

    public void drawColumnWithShed(SVG svg, int carpWidth, int carpLength, double columnThickness) {

    }

    private SVG drawArrows(SVG svg, int carpWidth, int carpLength) {
        svg.addArrows(50 + 6,100, 50 + 6, carpWidth + 100);
        svg.addArrows(100, carpWidth + 150 + 6, carpLength + 100, carpWidth + 150 +6 );
        return svg;
    }



}
