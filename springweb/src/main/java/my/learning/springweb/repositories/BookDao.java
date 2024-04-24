package my.learning.springweb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import my.learning.springweb.entities.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {

}
