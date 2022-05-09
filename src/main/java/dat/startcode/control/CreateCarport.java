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

        return "createCarport";
    }
}
