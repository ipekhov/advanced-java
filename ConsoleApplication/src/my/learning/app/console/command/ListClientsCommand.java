package my.learning.app.console.command;

import java.util.Set;

import my.learning.app.console.domain.Client;
import my.learning.app.console.repository.ClientRepository;

public class ListClientsCommand implements UserCommand {

	@Override
	public void execute() {
		Set<Client> clients = ClientRepository.getInstance().getAll();
		if(clients.isEmpty()) {
			System.out.println("No clients are found");
		} else {
			clients.forEach(System.out::println);
		}
	}

}
