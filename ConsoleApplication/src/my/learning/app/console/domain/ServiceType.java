package my.learning.app.console.domain;

public enum ServiceType {

	MULTIFACTOR("multiple"), FLAT_RATE("constant"), AVERAGE("average");

	private String strValue;

	ServiceType(String strValue) {
		this.strValue = strValue;
	}

	public String getStringValue() {
		return strValue;
	}
	
	public static ServiceType fromString(String string) {
		for(ServiceType type : ServiceType.values()) {
			if(type.strValue.equals(string)) {
				return type;
			}
		}
		return null;
	}
}
