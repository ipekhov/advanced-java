package my.learning.app.console.frontend;

import java.util.Arrays;

import my.learning.app.console.command.AddClientConsumptionCommand;
import my.learning.app.console.command.AddClientServiceCommand;
import my.learning.app.console.command.CalculateBillCommand;
import my.learning.app.console.command.CreateClientCommand;
import my.learning.app.console.command.CreateServiceCommand;
import my.learning.app.console.command.ListClientsCommand;
import my.learning.app.console.command.ListServicesCommand;
import my.learning.app.console.command.UserCommand;
import my.learning.app.console.domain.ServiceType;

public class UserCommandParser {
	
	private static final String CREATE_CLIENT_USER_CMD = "client";
	private static final String CREATE_SERVICE_USER_CMD = "service";
	private static final String ADD_CLIENT_SERVICE_USER_CMD = "add_client_service";
	private static final String ADD_CONSUMPTION_USER_CMD = "consumption";
	private static final String CALCULATE_BILL_USER_CMD = "bill";
	private static final String LIST_CLIENTS_USER_CMD = "clients";
	private static final String LIST_SERVICES_USER_CMD = "services";
	
	/**
	 * Public method for parsing user command from the input string
	 * 
	 * @param userCommandStr
	 * @return
	 * @throws CommandParseException
	 */
	public UserCommand parseCommand(String userCommandStr) throws CommandParseException {
		String[] commandWithArgs = userCommandStr.split("\\s+");
		String commandType = commandWithArgs[0];
		String[] cmdArgs = Arrays
				.copyOfRange(commandWithArgs, 1, commandWithArgs.length);
		
		switch(commandType) {
			case CREATE_CLIENT_USER_CMD:       return parseCreateClientCommand(cmdArgs);
			case CREATE_SERVICE_USER_CMD:      return parseCreateServiceCommand(cmdArgs);
			case LIST_CLIENTS_USER_CMD:        return parseListClientsCommand(cmdArgs);
			case LIST_SERVICES_USER_CMD:       return parseListServicesCommand(cmdArgs);
			case ADD_CLIENT_SERVICE_USER_CMD:  return parseAddClientServiceCommand(cmdArgs);
			case ADD_CONSUMPTION_USER_CMD:     return parseAddClientConsumptionCommand(cmdArgs);
			case CALCULATE_BILL_USER_CMD:      return parseCalculateBillCommand(cmdArgs);
			default: throw new CommandParseException("Unknown command type: " + commandType);
		}
	}

	/**
	 * Parse add client service command
	 * 
	 * @param args
	 * @return
	 * @throws CommandParseException
	 */
	private UserCommand parseAddClientServiceCommand(String[] args) throws CommandParseException {
		if(args.length != 2) {
			throw new CommandParseException("Incorrect number of arguments, two required");
		}
		if(!lettersOnly(args[0]) || !lettersOnly(args[1])) {
			throw new CommandParseException("Client and service names must only contain upper and lower case letters");
		}
		return new AddClientServiceCommand(args[0], args[1]);
	}

	/**
	 * Parse add client consumption command
	 * 
	 * @param args
	 * @return
	 * @throws CommandParseException
	 */
	private UserCommand parseAddClientConsumptionCommand(String[] args) throws CommandParseException {
		if(args.length != 3) {
			throw new CommandParseException("Incorrect number of arguments, three required");
		}
		if(!lettersOnly(args[0]) || !lettersOnly(args[1])) {
			throw new CommandParseException("Client and service names must only contain upper and lower case letters");
		}
		Double consumption = parseDouble(args[2], "Incorrect number format for consumption value");
		return new AddClientConsumptionCommand(args[0], args[1], consumption);
	}

	/**
	 * Parse calculate client's bill command
	 * 
	 * @param args
	 * @return
	 * @throws CommandParseException
	 */
	private UserCommand parseCalculateBillCommand(String[] args) throws CommandParseException {
		if(args.length != 1) {
			throw new CommandParseException("Incorrect number of arguments, one required");
		}
		if(!lettersOnly(args[0])) {
			throw new CommandParseException("Client names must only contain upper and lower case letters");
		}
		return new CalculateBillCommand(args[0]);
	}

	/**
	 * Parse command for printing the list of all clients
	 * 
	 * @param args
	 * @return
	 * @throws CommandParseException
	 */
	private UserCommand parseListClientsCommand(String[] args) throws CommandParseException {
		if(args.length > 0) {
			throw new CommandParseException("Too many arguments for the specified command, zero is required");
		}
		return new ListClientsCommand();
	}
	
	/**
	 * Parse command for printing the list of all services
	 * 
	 * @param args
	 * @return
	 * @throws CommandParseException
	 */
	private UserCommand parseListServicesCommand(String[] args) throws CommandParseException {
		if(args.length > 0) {
			throw new CommandParseException("Too many arguments for the specified command, zero is required");
		}
		return new ListServicesCommand();
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
		if(!lettersOnly(args[0])) {
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
		if(!lettersOnly(args[0])) {
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
			return new CreateServiceCommand(
					args[0],
					args[1],
					parseDouble(args[2], "Incorrent number format for service value")
					);
		}
		// Parse double values and calculate average
		Double avg = 0.0;
		for(int i = 2; i < args.length; i++) {
			avg += ( parseDouble(args[i], "Incorrent number format for service value") / args.length );
		}
		return new CreateServiceCommand(args[0], args[1], avg);
	}
	
	/**
	 * Check whether the string only contains letters
	 * 
	 * @param arg
	 * @return
	 */
	private boolean lettersOnly(String arg) {
		return arg.matches("[A-Za-z]+");
	}
	
	/**
	 * Parse double from string or throw exception
	 * 
	 * @param value
	 * @return
	 * @throws CommandParseException
	 */
	private Double parseDouble(String value, String message) throws CommandParseException {
		try {
			return Double.valueOf(value);
		} catch(NumberFormatException e) {
			throw new CommandParseException(message);
		}
		
	}

}
