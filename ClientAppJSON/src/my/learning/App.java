package my.learning;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class App {
	
	private static final String URL = "http://localhost:8080/WebProjectSample2/users";

	public static void main(String[] args) throws MalformedURLException, IOException {

		String resp = IOUtils.toString(new URL(URL), Charset.forName("UTF-8"));
		System.out.println(resp);
		
		Gson gson = new Gson();
		var users = gson.fromJson(resp, User[].class);
		
		for(User user : users) {
			System.out.println(user);
		}
	}

}
