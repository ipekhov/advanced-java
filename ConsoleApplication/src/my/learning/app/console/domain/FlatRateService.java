package my.learning.app.console.domain;

import java.util.Optional;

public class FlatRateService extends Service {

	private final Double rate;

	public FlatRateService(String name, Double rate) {
		super(name);
		this.rate = rate;
	}

	public Double getRate() {
		return rate;
	}

	@Override
	public Double calculateBill(Optional<ServiceConsumption> consumption) {
		return rate;
	}

	@Override
	public String getTypeStr() {
		return "constant";
	}

}
