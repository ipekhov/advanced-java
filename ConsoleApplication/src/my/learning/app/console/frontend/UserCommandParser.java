package my.learning.app.console.frontend;

import java.util.Arrays;

import my.learning.app.console.command.CreateClientCommand;
import my.learning.app.console.command.UserCommand;

public class UserCommandParser {
	
	private static final String CREATE_CLIENT_USER_CMD = "client";
	private static final String CREATE_SERVICE_USER_CMD = "service";
	private static final String ADD_CLIENT_SERVICE_USER_CMD = "add_client_service";
	private static final String ADD_CONSUMPTION_USER_CMD = "consumption";
	private static final String CALCULATE_BILL_USER_CMD = "bill";
	private static final String LIST_CLIENTS_USER_CMD = "services";
	private static final String LIST_SERVICES_USER_CMD = "clients";
	
	public UserCommand parseCommand(String userCommandStr) throws CommandParseException {
		String[] commandWithArgs = userCommandStr.split("\\s+");
		String[] cmdArgs = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);
		String commandType = commandWithArgs[0];
		
		switch(commandType) {
			case CREATE_CLIENT_USER_CMD: return parseCreateClientCommand(cmdArgs);
			default: throw new CommandParseException("Unknown command type: " + commandType);
		}
	}
	
	private CreateClientCommand parseCreateClientCommand(String[] args) throws CommandParseException {
		if(args.length == 0) {
			throw new CommandParseException("Client name needs to be specified");
		}
		if(args.length > 1) {
			throw new CommandParseException("Too many arguments for the client command, one is required");
		}
		if(!args[0].matches("[A-Z][a-z]+")) {
			throw new CommandParseException("Client name must conatin only upper and lower case letters");
		}
		return new CreateClientCommand(args[0]);
	}

}
