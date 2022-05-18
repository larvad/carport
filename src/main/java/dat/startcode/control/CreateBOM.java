package dat.startcode.control;

import dat.startcode.model.config.ApplicationStart;
import dat.startcode.model.dto.BomDTO;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CreateBOM extends Command {
    private ConnectionPool connectionPool;

    public CreateBOM() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {

        int orderId = Integer.parseInt(request.getParameter("BOM"));
        List<BomDTO> BOMtagogtræ = UserFacade.showBOMTraeOgTagplader(orderId,connectionPool);
        request.setAttribute("BOMtagogtræ",BOMtagogtræ);

        List<BomDTO> bomSkruerOgBeslag = UserFacade.showBOMSkruerOgBeslag(orderId,connectionPool);
        request.setAttribute("BOMSkruerOgBeslag",bomSkruerOgBeslag);

        return "ShowBOM";
    }
}
