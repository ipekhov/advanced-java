package my.learning.app.console.command;

import my.learning.app.console.domain.AverageValueService;
import my.learning.app.console.domain.FlatRateService;
import my.learning.app.console.domain.MultipleFactoredService;
import my.learning.app.console.domain.ServiceType;
import my.learning.app.console.repository.RepositoryAlreadyExistsException;
import my.learning.app.console.repository.ServiceRepository;

public class CreateServiceCommand implements UserCommand {

	private final String serviceName;
	private final ServiceType serviceType;
	private final Double value;

	public CreateServiceCommand(String serviceName, String serviceType, Double value) {
		this.serviceName = serviceName;
		this.serviceType = ServiceType.fromString(serviceType);
		this.value = value;
	}

	@Override
	public void execute() {
		ServiceRepository repo = ServiceRepository.getInstance();
		try {
			switch (this.serviceType) {
				case AVERAGE: {
					repo.add(new AverageValueService(serviceName, value));
					break;
				}
				case FLAT_RATE: {
					repo.add(new FlatRateService(serviceName, value));
					break;
				}
				case MULTIFACTOR: {
					repo.add(new MultipleFactoredService(serviceName, value));
					break;
				}
			}
			System.out.println("Created service " + serviceName + ".");
		} catch(RepositoryAlreadyExistsException e) {
			System.out.println("Service with name " + this.serviceName + " already exists.");
		}
	}

}
