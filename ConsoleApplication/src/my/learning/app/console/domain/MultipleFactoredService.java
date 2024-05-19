package my.learning.app.console.domain;

import java.util.Optional;

public class MultipleFactoredService extends Service {

	private final Double factor;

	public MultipleFactoredService(String name, Double factor) {
		super(name);
		this.factor = factor;
	}

	public Double getFactor() {
		return factor;
	}

	@Override
	public Double calculateBill(Optional<ServiceConsumption> consumption) {
		if(consumption.isEmpty()) {
			throw new RuntimeException("Consumption is required for multiple factor service " + this.getName());
		}
		return factor * consumption.get().getConsumption();
	}

	@Override
	public String getTypeStr() {
		return "multiple";
	}

}
