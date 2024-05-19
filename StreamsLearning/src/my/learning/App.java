package my.learning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Predicate;

record Person(String firstName, String lastName, int age) implements Comparable<Person> {

	@Override
	public int compareTo(Person person) {
		return Comparator.comparing(Person::firstName).thenComparing(Person::lastName).compare(this, person);
	}

}

public class App {

	public static void main(String[] args) throws IOException {

		// -------------------- Records testing ----------------------//
		Person p1 = new Person("John", "Wayne", 38);
		Person p2 = new Person("John", "Maxwell", 39);
		Person p3 = new Person("Aaron", "Johnson", 37);
		Person p4 = new Person("Gerald", "Wonders", 38);
		Person p5 = new Person("Zooey", "Banks", 38);
		Person p6 = new Person("Brad", "Johnson", 37);
		Person p7 = new Person("Brad", "Zemekis", 37);

		ArrayList<Person> people = new ArrayList<>();
		people.add(p1);
		people.add(p2);
		people.add(p3);
		people.add(p4);
		people.add(p5);
		people.add(p6);
		people.add(p7);

		people.stream().sorted().forEach(System.out::println);

		// System.out.println(p1);
		System.out.println(p1.getClass().getSuperclass());

		// -------------------- Streams testing ----------------------//

		System.out.println("\n-------------------- Streams testing ----------------------");

		var contents = Files.lines(Paths.get("names.txt"));

		/*
		 * var numLines = contents.count(); System.out.println("Number of lines: " +
		 * numLines);
		 */

		/*
		 * var contentsAsStr = Files.readString(Paths.get("names.txt")); List<String>
		 * words = List.of(contentsAsStr.split("\\PL+"));
		 * System.out.println(words.size());
		 */

		/*
		 * var count = contents.filter(word -> word.length() < 4).count();
		 * System.out.println(count);
		 */

		Predicate<String> predicate1 = new Predicate<>() {

			@Override
			public boolean test(String t) {
				return t.length() > 12;
			}

		};

		contents.filter(predicate1)
				.sorted(Comparator.comparingInt(String::length).reversed())
				.forEach(System.out::println);

		contents.close();
		

		// -------------------- Anonymous inner class testing ----------------------//
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("Running...");
			}
			
		};
		runnable.run();
		System.out.println(runnable.getClass());

	}

}
