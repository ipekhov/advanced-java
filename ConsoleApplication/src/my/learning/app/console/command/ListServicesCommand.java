package my.learning.app.console.command;

import java.util.Set;

import my.learning.app.console.domain.Service;
import my.learning.app.console.repository.ServiceRepository;

public class ListServicesCommand implements UserCommand {

	@Override
	public void execute() {
		Set<Service> services = ServiceRepository.getInstance().getAll();
		if(services.isEmpty()) {
			System.out.println("No services are found");
		} else {
			services.forEach(s -> { 
				System.out.println(s.getName() + " " + s.getServiceType().getStringValue() + " with rate " + s.getRate()); 
			});
		}
	}

}
