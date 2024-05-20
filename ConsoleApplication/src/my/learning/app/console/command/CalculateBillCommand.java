package my.learning.app.console.command;

import my.learning.app.console.domain.Client;
import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.service.BillingService;

public class CalculateBillCommand implements UserCommand {
	
	private final String clientName;

	public CalculateBillCommand(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public void execute() {
		BillingService billingService = BillingService.getInstance();
		Client client = ClientRepository.getInstance().findByName(clientName);
		
		if(client == null) {
			System.out.println("Client woth name " + clientName + " does not exist, operation cancelled.");
			return;
		}
		System.out.println("Amount due for client " + clientName + ": " + billingService.calculateClientBill(client));
	}

}
