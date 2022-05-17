package dat.startcode.control;

import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redirect extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        String page = request.getParameter("page");
        return page;
    }
}
