package dat.startcode.control;

import dat.startcode.logic.RequestCalculator;
import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends Command {
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        //TODO: hent en DTO fra databasen til visning i tabel på admin.jsp

        return "admin";
    }
}
