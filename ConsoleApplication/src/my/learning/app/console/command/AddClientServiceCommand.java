package my.learning.app.console.command;

import java.util.Set;

import my.learning.app.console.domain.Client;
import my.learning.app.console.domain.Service;
import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.repository.ServiceRepository;

public class AddClientServiceCommand implements UserCommand {
	
	private final String clientName; 
	private final String serviceName;

	public AddClientServiceCommand(String clientName, String serviceName) {
		this.clientName = clientName;
		this.serviceName = serviceName;
	}

	@Override
	public void execute() {
		Client client   = ClientRepository.getInstance().findByName(clientName);
		Service service = ServiceRepository.getInstance().findByName(serviceName);
		
		if(client == null) {
			System.out.println("Client with name " + clientName + " does not exist, operation cancelled.");
			return;
		}
		if(service == null) {
			System.out.println("Service with name " + serviceName + " is not defined, operation cancelled.");
			return;
		}
		client.addService(service);
	}

}
