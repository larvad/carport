package dat.startcode.control;

import dat.startcode.logic.RequestCalculator;
import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCarport extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        RequestCalculator requestCalculator = new RequestCalculator();
        requestCalculator.calculate();




        //TODO: CHECK om redskabsrum er 30cm mindre i brede og længe! ellers send kunde tilbage i børnehaven.
        return "createCarport";
    }
}
