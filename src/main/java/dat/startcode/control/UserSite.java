package dat.startcode.control;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;
import dat.startcode.model.persistence.ConnectionPool;
import dat.startcode.model.services.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserSite extends Command{

    private ConnectionPool connectionPool;

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DatabaseException {
        HttpSession session = request.getSession();

        //henter info fra formularen
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rpPassword = request.getParameter("rppassword");
        String fullname = request.getParameter("fullname");
        String adress = request.getParameter("adress");
        int phoneNo = Integer.parseInt(request.getParameter("phoneno"));

        //TJekker om de to password er ens
        if (!password.equals(rpPassword)) {
            //TODO: sæt fejlbeskeden til inde på jsp-siden
            request.setAttribute("fejlTilIndex", "Kodeordene var ikke ens, prøv igen");
            return "createUser";
        }

        //TODO: Tjekker om emailadressen allerede er oprettet
//        UserMapper userMapper = new UserMapper(connectionPool);
//        try {
//            Map<String, CustomerDTO> userMap = userMapper.getUserMapByEmail();
//            if (userMap.containsKey(email)) {
//                request.setAttribute("fejlTilIndex", "Emailadressen " + email + " er allerede oprettet i systemet");
//                return "createUser";
//            }
//        } catch (DatabaseException e) {
//            e.printStackTrace();
//        }

        //Opretter brugeren i databasen
        User user = UserFacade.createUser(fullname, email, password, phoneNo, adress, connectionPool);

        //Logger brugeren på
        session.setAttribute("user", user);

        return "createCarport";
    }
}
