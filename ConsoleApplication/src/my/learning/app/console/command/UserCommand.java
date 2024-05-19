package my.learning.app.console.command;

import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.repository.ServiceRepository;
import my.learning.app.console.service.BillingService;

public abstract class UserCommand {
	
	protected final ClientRepository clientRepository = new ClientRepository();
	protected final ServiceRepository serviceRepository = new ServiceRepository();
	protected final BillingService billingService = new BillingService();

	public UserCommand() {
	}

	public abstract void execute();
	
}
