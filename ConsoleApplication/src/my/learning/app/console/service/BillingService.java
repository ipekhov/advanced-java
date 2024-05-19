package my.learning.app.console.service;


import java.util.Optional;

import my.learning.app.console.domain.Client;
import my.learning.app.console.domain.Service;
import my.learning.app.console.domain.ServiceConsumption;
import my.learning.app.console.repository.ServiceRepository;

public class BillingService {
	
	private static final BillingService INSTANCE = new BillingService();
	
	private BillingService() {}

	public static synchronized BillingService getInstance() {
		return INSTANCE;
	}
	
	public Double calculateClientBill(Client client) {
		double total = 0.0;
		for(Service service : client.getServices()) {
			Optional<ServiceConsumption> consumption = Optional.ofNullable(client.getConsumptionForService(service));
			total += service.calculateBill(consumption);
		}
		return total;
	}

}
