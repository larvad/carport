package dat.startcode.control;

import dat.startcode.model.exceptions.DatabaseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

abstract class Command
{

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("about", new About());
        commands.put("createCarport", new CreateCarport());
        commands.put("createUser", new CreateUser());
        commands.put("sendInquiry", new SendInquiry());
        commands.put("profile", new Profile());

    }



    static Command from( HttpServletRequest request ) {
        String commandName = request.getParameter( "command" );
        if ( commands == null ) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand() );   // unknowncommand er default.
    }

    abstract String execute( HttpServletRequest request, HttpServletResponse response ) 
            throws DatabaseException;

}
