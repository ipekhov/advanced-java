package my.learning.app.console.domain;

import java.util.Objects;

public class ServiceConsumption implements Named {
	
	private final Client client;
	private final Service service;
	private final Double consumption;
	private final String name;
	
	public ServiceConsumption(Client client, Service service, Double consumption) {
		this.client = client;
		this.service = service;
		this.consumption = consumption;
		this.name = new StringBuilder()
				.append(client.getName())
				.append("_")
				.append(service.getName())
				.toString();
	}

	@Override
	public String getName() {
		return this.name;
	}

	public Client getClient() {
		return client;
	}

	public Service getService() {
		return service;
	}

	public Double getConsumption() {
		return consumption;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceConsumption other = (ServiceConsumption) obj;
		return Objects.equals(name, other.name);
	}

    @Override
	public String toString() {
		return "ServiceConsumption[" + name + ": " + consumption + "]";
	}

}
