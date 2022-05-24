package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarport extends Command {
    private ConnectionPool connectionPool;

    public CreateCarport() {
       this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

//        List<Materials> flatRoofMaterialsList = null;
//        List<Materials> raisedRoofMaterialsList = null;
//
//
//        try {
//            flatRoofMaterialsList = Facade.showFlatRoofMaterial(connectionPool);
//            raisedRoofMaterialsList = Facade.showRaisedRoofMaterial(connectionPool);
//
//        } catch (DatabaseException e) {
//            e.printStackTrace();
//        }
//
//        ServletContext servletContext = request.getServletContext();
//        servletContext.setAttribute("flatRoofMaterialsList",flatRoofMaterialsList);
//        servletContext.setAttribute("raisedRoofMaterialsList",raisedRoofMaterialsList);

        return "createCarport";
    }
}
