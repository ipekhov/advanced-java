package my.learning.mysql;

import java.io.IOException;
import java.util.Properties;

public class Profile {
	
	public static Properties getProperties(String name) {
		Properties props = new Properties();
		String env = System.getProperty("env");
		if(env == null) {
			env = "dev";
		}
		String propsFile = String.format("/config/%s.%s.properties", name, env);
		System.out.println("Properties file: " + propsFile);
		try {
			props.load(App.class.getResourceAsStream(propsFile));
		} catch (IOException e) {
			throw new RuntimeException("Cannot load properties file: " + propsFile);
		}
		return props;
	}

}
