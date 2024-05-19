package my.learning.app.console;

import my.learning.app.console.domain.AverageValueService;
import my.learning.app.console.domain.Client;
import my.learning.app.console.domain.Service;
import my.learning.app.console.domain.ServiceConsumption;
import my.learning.app.console.domain.FlatRateService;
import my.learning.app.console.domain.MultipleFactoredService;
import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.repository.ServiceRepository;
import my.learning.app.console.service.BillingService;

public class Application {
	
	private ClientRepository clientRepository = new ClientRepository();
	
	private ServiceRepository serviceRepository = new ServiceRepository();
	
	private BillingService billingService = new BillingService();
	

	public static void main(String[] args) {

		Application app = new Application();
		
		Client cli1 = new Client("John");
		Service serv1 = new FlatRateService("phone", 50.0);
		Service serv2 = new MultipleFactoredService("electricity", 0.005);
		Service serv3 = new AverageValueService("average_rate_service", 125.0, 21.0, 31.0);
		cli1
			.addService(serv1)
			.addService(serv2)
			.addService(serv3)
			.addConsumption(new ServiceConsumption(cli1, serv2, 3010.0));
		
		System.out.println(app.billingService.calculateClientBill(cli1));
	}

}
