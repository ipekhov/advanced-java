package my.learning.app.console.domain;

import java.util.Optional;

public class MultipleFactoredService extends Service {

	private final Double rate;

	public MultipleFactoredService(String name, Double factor) {
		super(name);
		this.rate = factor;
	}

	@Override
	public Double getRate() {
		return rate;
	}

	@Override
	public Double calculateBill(Optional<ServiceConsumption> consumption) {
		if(consumption.isEmpty()) {
			throw new IllegalArgumentException("Consumption is required for multiple factor service " + this.getName());
		}
		return rate * consumption.get().getConsumption();
	}

	@Override
	public ServiceType getServiceType() {
		return ServiceType.MULTIFACTOR;
	}
		

}
