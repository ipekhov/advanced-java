package my.learning.app.console.domain;

import java.util.Objects;
import java.util.Optional;

public abstract class Service implements Named {

	private final String name;
	
	public Service(String name) {
		this.name = name;
	}
	
	public abstract Double calculateBill(Optional<ServiceConsumption> consumption);
	
	public abstract String getTypeStr();
	
	@Override
	public String getName() {
		return this.name;
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
		Service other = (Service) obj;
		return Objects.equals(name, other.name);
	}
}
