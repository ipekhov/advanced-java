package my.learning.app.console;

import java.util.Scanner;

import my.learning.app.console.command.UserCommand;
import my.learning.app.console.domain.AverageValueService;
import my.learning.app.console.domain.Client;
import my.learning.app.console.domain.Service;
import my.learning.app.console.domain.ServiceConsumption;
import my.learning.app.console.frontend.CommandParseException;
import my.learning.app.console.frontend.UserCommandParser;
import my.learning.app.console.repository.ClientRepository;
import my.learning.app.console.repository.RepositoryAlreadyExistsException;
import my.learning.app.console.repository.ServiceRepository;
import my.learning.app.console.service.BillingService;
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
		
		test();
		
		System.out.println("\n=========== Enter command, type exit to quit ===========");
		
		while(true) {
			System.out.print(">");
			String input = scanner.nextLine();
			if(input.equals("exit")) {
				System.out.println("Exited");
				break;
			} else if(input.isBlank()) {
				continue;
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
	
	/**
	 * Creating example objects for testing
	 */
	private static void test() {
		
		ClientRepository cliRepo = ClientRepository.getInstance();
		ServiceRepository servRepo = ServiceRepository.getInstance();
		BillingService billService = BillingService.getInstance();
		
		try {
			Client cli1 = new Client("John");
			cliRepo.add(cli1);
			
			Client cli2 = new Client("Kate");
			cliRepo.add(cli2);
			
			Client cli3 = new Client("Alex");
			cliRepo.add(cli3);
			
			Service serv1 = new FlatRateService("phone", 50.0);
			servRepo.add(serv1);
			
			Service serv2 = new MultipleFactoredService("electricity", 0.005);
			servRepo.add(serv2);
			
			Service serv3 = new AverageValueService("average_rate_service", 125.0, 21.0, 31.0);
			servRepo.add(serv3);
			
			cli1
				.addService(serv1)
				.addService(serv2)
				.addService(serv3)
				.addConsumption(new ServiceConsumption(cli1, serv2, 3010.0));
			
			cli2
				.addService(serv1)
				.addService(serv2)
				.addConsumption(new ServiceConsumption(cli1, serv2, 2010.0));
			
		    System.out.println(billService.calculateClientBill(cli1));
		    System.out.println(billService.calculateClientBill(cli2));
			
		} catch (RepositoryAlreadyExistsException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
	}

}
