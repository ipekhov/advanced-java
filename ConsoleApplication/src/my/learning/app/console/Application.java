package my.learning.app.console;

import java.util.Scanner;

import my.learning.app.console.command.UserCommand;
import my.learning.app.console.domain.AverageValueService;
import my.learning.app.console.domain.Client;
import my.learning.app.console.domain.Service;
import my.learning.app.console.domain.ServiceConsumption;
import my.learning.app.console.frontend.CommandParseException;
import my.learning.app.console.frontend.UserCommandParser;
import my.learning.app.console.domain.FlatRateService;
import my.learning.app.console.domain.MultipleFactoredService;

public class Application {
	

	/**
	 * Entry point to the application, prompts for user input, calls the command parser, 
	 * executes parsed user command and prints the output to console
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		UserCommandParser cmdParser = new UserCommandParser();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=========== Enter command, type exit to quit ===========");
		
		while(true) {
			System.out.print(">");
			String input = scanner.nextLine();
			if(input.equals("exit")) {
				System.out.println("Exited");
				break;
			}
			try {
				UserCommand cmd = cmdParser.parseCommand(input);
				cmd.execute();
			} catch (CommandParseException | IllegalArgumentException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
		scanner.close();
	}
	
	private static void test() {

		Application app = new Application();
		
		Client cli1 = new Client("John");
		Service serv1 = new FlatRateService("phone", 50.0);
		Service serv2 = new MultipleFactoredService("electricity", 0.005);
		Service serv3 = new AverageValueService("average_rate_service", 125.0, 21.0, 31.0);
		cli1
			.addService(serv1)
			.addService(serv2)
			.addService(serv3)
			.addConsumption(new ServiceConsumption(cli1, serv2, 3010.0));
		
		// System.out.println(app.billingService.calculateClientBill(cli1));
	}

}
