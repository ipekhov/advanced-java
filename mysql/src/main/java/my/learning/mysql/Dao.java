package my.learning.mysql;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	void save(T t);
	
	Optional<T> findById(int id);
	
	void update(T t);
	
	void delete(T t);

	public List<User> findAll();

}
