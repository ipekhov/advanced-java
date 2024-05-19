package my.learning.app.console.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Client implements Named {

	private final String name;
	private final Set<Service> services = new HashSet<>();
	private final Set<ServiceConsumption> consumptions = new HashSet<>();
	
	public Client(String name) {
		this.name = name;
	}
	
	public Client addService(Service service) {
		if(this.services.contains(service)) {
			throw new RuntimeException("Client " + this.name + " already has service " + service);
		}
		this.services.add(service);
		return this;
	}
	
	public Client addConsumption(ServiceConsumption consumption) {
		if(this.consumptions.contains(consumptions)) {
			throw new RuntimeException("Client " + this.name + " already has consumption for service " + consumption.getService());
		}
		this.consumptions.add(consumption);
		return this;
	}
	
	public ServiceConsumption getConsumptionForService(Service service) {
		for(var c : this.consumptions) {
			if(c.getService().equals(service)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public String getName() {
		return name;
	}

	public Set<Service> getServices() {
		return services;
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
		Client other = (Client) obj;
		return Objects.equals(name, other.name);
	}
	
}
