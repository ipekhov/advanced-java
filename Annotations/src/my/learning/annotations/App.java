package my.learning.annotations;

public class App {

	public static void main(String[] args) {
		
		var user = new User(1L, "Thor");
		var repo = new Repository<User>();
		repo.save(user);

	}

}
