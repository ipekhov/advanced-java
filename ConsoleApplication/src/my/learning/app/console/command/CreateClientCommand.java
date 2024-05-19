package my.learning.app.console.command;

import my.learning.app.console.domain.Client;

public class CreateClientCommand extends UserCommand {
	
	private final String clientName;

	public CreateClientCommand(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public void execute() {
		clientRepository.add(new Client(this.clientName));
	}

}
