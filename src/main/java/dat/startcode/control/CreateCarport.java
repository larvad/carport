package dat.startcode.control;

import dat.startcode.logic.RequestCalculator;
import dat.startcode.model.entities.Materials;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class CreateCarport extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        RequestCalculator requestCalculator = new RequestCalculator();

        //TODO brug facaden og connectionpool derfra
        ConnectionPool connectionPool = new ConnectionPool();
        requestCalculator.calculate(connectionPool);




        //TODO: CHECK om redskabsrum er 30cm mindre i brede og længe! ellers send kunde tilbage i børnehaven.
        return "createCarport";
    }
}
