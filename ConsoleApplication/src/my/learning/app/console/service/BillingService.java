package my.learning.app.console.service;


import java.util.Optional;

import my.learning.app.console.domain.Client;
import my.learning.app.console.domain.Service;
import my.learning.app.console.domain.ServiceConsumption;

public class BillingService {
	
	public Double calculateClientBill(Client client) {
		double total = 0.0;
		for(Service service : client.getServices()) {
			Optional<ServiceConsumption> consumption = Optional.ofNullable(client.getConsumptionForService(service));
			total += service.calculateBill(consumption);
		}
		return total;
	}

}
