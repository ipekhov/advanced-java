package my.learning.app.console.repository;

import my.learning.app.console.domain.Service;

public class ServiceRepository extends NamedEntryRepository<Service> {
	
	private static final ServiceRepository INSTANCE = new ServiceRepository();
	
	private ServiceRepository() {}

	public static synchronized ServiceRepository getInstance() {
		return INSTANCE;
	}

}
