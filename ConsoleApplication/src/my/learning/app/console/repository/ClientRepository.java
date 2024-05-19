package my.learning.app.console.repository;

import my.learning.app.console.domain.Client;

public class ClientRepository extends NamedEntryRepository<Client> {
	
	private static final ClientRepository INSTANCE = new ClientRepository();
	
	private ClientRepository() {}

	public static synchronized ClientRepository getInstance() {
		return INSTANCE;
	}

}
