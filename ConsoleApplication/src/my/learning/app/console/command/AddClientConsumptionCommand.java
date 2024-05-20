package my.learning.app.console.command;

import my.learning.app.console.domain.Client;
import my.learning.app.console.domain.Service;
import my.learning.app.console.domain.ServiceConsumption;
import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.repository.ServiceRepository;

public class AddClientConsumptionCommand implements UserCommand {
	
	private String clientName;
	private String serviceName;
	private Double consumption;

	public AddClientConsumptionCommand(String clientName, String serviceName, Double consumption) {
		this.clientName = clientName;
		this.serviceName = serviceName;
		this.consumption = consumption;
	}

	@Override
	public void execute() {
		Client client = ClientRepository.getInstance().findByName(clientName);
		Service service = ServiceRepository.getInstance().findByName(serviceName);
		
		if(client == null) {
			System.out.println("Client with name " + clientName + " does not exist, operation cancelled.");
			return;
		}
		if(service == null) {
			System.out.println("Service with name " + serviceName + " is not defined, operation cancelled.");
			return;
		}
		if(!client.getServices().contains(service)) {
			System.out.println("Client with name " + clientName + " does not have service " + serviceName + ", operation cancelled.");
			return;
		}
		client.addConsumption(new ServiceConsumption(client, service, consumption));		
	}

}
