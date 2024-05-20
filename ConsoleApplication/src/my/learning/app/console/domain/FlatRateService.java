package my.learning.app.console.domain;

import java.util.Optional;

public class FlatRateService extends Service {

	private final Double rate;

	public FlatRateService(String name, Double rate) {
		super(name);
		this.rate = rate;
	}

	@Override
	public Double getRate() {
		return rate;
	}

	@Override
	public Double calculateBill(Optional<ServiceConsumption> consumption) {
		return rate;
	}

	@Override
	public ServiceType getServiceType() {
		return ServiceType.FLAT_RATE;
	}

}
