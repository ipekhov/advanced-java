package my.learning.app.console.command;

import my.learning.app.console.domain.Client;
import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.repository.RepositoryAlreadyExistsException;

public class CreateClientCommand implements UserCommand {
	
	private final String clientName;

	public CreateClientCommand(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public void execute() {
		try {
			ClientRepository.getInstance().add(new Client(this.clientName));
			System.out.println("Created client " + this.clientName + ".");
		} catch (RepositoryAlreadyExistsException e) {
			System.out.println("Client with name " + this.clientName + " already exists.");
		}
	}

}
