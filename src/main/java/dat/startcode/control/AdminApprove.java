package dat.startcode.control;

import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminApprove extends Command{
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        return "adminApprove";
    }
}
