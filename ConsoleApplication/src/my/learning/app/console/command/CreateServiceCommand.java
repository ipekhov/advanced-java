package my.learning.app.console.command;

import javax.imageio.spi.ServiceRegistry;

import my.learning.app.console.domain.AverageValueService;
import my.learning.app.console.domain.FlatRateService;
import my.learning.app.console.domain.MultipleFactoredService;
import my.learning.app.console.domain.ServiceType;
import my.learning.app.console.repository.RepositoryStoreException;
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
					return;
				}
				case FLAT_RATE: {
					repo.add(new FlatRateService(serviceName, value));
					return;
				}
				case MULTIFACTOR: {
					repo.add(new MultipleFactoredService(serviceName, value));
					return;
				}
			}
		} catch(RepositoryStoreException e) {
			System.out.println(e.getMessage());
		}
	}

}
