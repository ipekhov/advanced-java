package my.learning.app.console.command;

import my.learning.app.console.frontend.CommandParseException;

public class CreateServiceCommand extends UserCommand {
	
	private final String serviceName;
	private final String serviceType;
	private final Double[] values;

	public CreateServiceCommand(String serviceName, String serviceType, Double[] values) {
		this.serviceName = serviceName;
		this.serviceType = serviceName;
		this.values = values;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
