package my.learning.app.console.command;

import my.learning.app.console.domain.Client;
import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.repository.RepositoryStoreException;

public class CreateClientCommand implements UserCommand {
	
	private final String clientName;

	public CreateClientCommand(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public void execute() {
		try {
			ClientRepository.getInstance().add(new Client(this.clientName));
		} catch (RepositoryStoreException e) {
			System.out.println(e.getMessage());
		}
	}

}
