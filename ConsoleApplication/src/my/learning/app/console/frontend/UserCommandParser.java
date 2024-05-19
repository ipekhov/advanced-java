package my.learning.app.console.frontend;

import java.util.Arrays;

import my.learning.app.console.command.CreateClientCommand;
import my.learning.app.console.command.CreateServiceCommand;
import my.learning.app.console.command.UserCommand;
import my.learning.app.console.domain.ServiceType;

public class UserCommandParser {
	
	private static final String CREATE_CLIENT_USER_CMD = "client";
	private static final String CREATE_SERVICE_USER_CMD = "service";
	private static final String ADD_CLIENT_SERVICE_USER_CMD = "add_client_service";
	private static final String ADD_CONSUMPTION_USER_CMD = "consumption";
	private static final String CALCULATE_BILL_USER_CMD = "bill";
	private static final String LIST_CLIENTS_USER_CMD = "services";
	private static final String LIST_SERVICES_USER_CMD = "clients";
	
	/**
	 * Public method for parsing user command from the input string
	 * 
	 * @param userCommandStr
	 * @return
	 * @throws CommandParseException
	 */
	public UserCommand parseCommand(String userCommandStr) throws CommandParseException {
		String[] commandWithArgs = userCommandStr.split("\\s+");
		String[] cmdArgs = Arrays.copyOfRange(commandWithArgs, 1, commandWithArgs.length);
		String commandType = commandWithArgs[0];
		
		switch(commandType) {
			case CREATE_CLIENT_USER_CMD:  return parseCreateClientCommand(cmdArgs);
			case CREATE_SERVICE_USER_CMD: return parseCreateServiceCommand(cmdArgs);
			default: throw new CommandParseException("Unknown command type: " + commandType);
		}
	}
	
	/**
	 * Parse command for creating new client
	 * 
	 * @param args
	 * @return
	 * @throws CommandParseException
	 */
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
	
	/**
	 * Parse command for creating new service
	 * 
	 * @param args
	 * @return
	 * @throws CommandParseException
	 */
	private CreateServiceCommand parseCreateServiceCommand(String[] args) throws CommandParseException {
		if(args.length < 3) {
			throw new CommandParseException("Service name, service type and values need to be specified");
		}
		if(!args[0].matches("[A-Z][a-z]+")) {
			throw new CommandParseException("Service name must conatin only upper and lower case letters");
		}
		boolean isMultiple = args[1].equals(ServiceType.MULTIFACTOR.getStringValue());
		boolean isConstant = args[1].equals(ServiceType.FLAT_RATE.getStringValue());
		boolean isAverage = args[1].equals(ServiceType.AVERAGE.getStringValue());
		if(!isMultiple && !isConstant && !isAverage) {
			throw new CommandParseException("Unknown service type " + args[1] + ", must be in multiple|constant|average");
		}
		if(isMultiple || isConstant) {
			if(args.length > 3) {
				throw new CommandParseException("Multple factored and constant services name must have only one value specified");
			}
			return new CreateServiceCommand(args[0], args[1], parseDouble(args[2]));
		}
		// Parse double values and calculate average
		Double avg = 0.0;
		for(int i = 2; i < args.length; i++) {
			avg += ( parseDouble(args[i]) / args.length );
		}
		return new CreateServiceCommand(args[0], args[1], avg);
	}
	
	/**
	 * Parse double from string or throw exception
	 * 
	 * @param value
	 * @return
	 * @throws CommandParseException
	 */
	private Double parseDouble(String value) throws CommandParseException {
		try {
			return Double.valueOf(value);
		} catch(NumberFormatException e) {
			throw new CommandParseException(e.getMessage());
		}
		
	}

}
