package dat.startcode.control;

import dat.startcode.logic.RequestCalculator;
import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.entities.Materials;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.persistence.RequestMapper;
import dat.startcode.model.services.UserFacade;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

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
//            flatRoofMaterialsList = UserFacade.showFlatRoofMaterial(connectionPool);
//            raisedRoofMaterialsList = UserFacade.showRaisedRoofMaterial(connectionPool);
//
//        } catch (DatabaseException e) {
//            e.printStackTrace();
//        }
//
//        ServletContext servletContext = request.getServletContext();
//        servletContext.setAttribute("flatRoofMaterialsList",flatRoofMaterialsList);
//        servletContext.setAttribute("raisedRoofMaterialsList",raisedRoofMaterialsList);



        //TODO: CHECK om redskabsrum er 30cm mindre i brede og længe! ellers send kunde tilbage i børnehaven.
        return "createCarport";
    }
}
