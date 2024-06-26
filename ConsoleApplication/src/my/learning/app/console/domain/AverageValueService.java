package my.learning.app.console.domain;

import java.util.Optional;

public class AverageValueService extends Service {
	
	private final Double[] values;

	public AverageValueService(String name, Double... values) {
		super(name);
		this.values = values;
	}

	public Double[] getValues() {
		return values;
	}

	@Override
	public Double calculateBill(Optional<ServiceConsumption> consumption) {
		double sum = 0.0;
		for(int i = 0; i < values.length; i++) {
			sum += values[i];
		}
		return sum / values.length;
	}

	@Override
	public ServiceType getServiceType() {
		return ServiceType.AVERAGE;
	}

	@Override
	public Double getRate() {
		Double avg = 0.0;
		for(Double value : this.values) {
			avg += ( value / values.length );
		}
		return avg;
	}

}
