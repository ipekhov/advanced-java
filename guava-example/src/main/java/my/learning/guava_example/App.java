package my.learning.guava_example;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String[] args) {
		
		Multiset<String> animals = HashMultiset.create();
		
		animals.add("wolf");
		animals.add("wolf");
		animals.add("rabbit");
		
		animals.forEach(System.out::println);
	}
}
