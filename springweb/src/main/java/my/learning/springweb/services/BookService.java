package my.learning.springweb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.learning.springweb.entities.Book;
import my.learning.springweb.repositories.BookDao;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;

	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		bookDao.findAll()
				.forEach(book -> books.add(book));
		return books;
	}
	
	public Book findById(Long id) {
		var bookOpt = bookDao.findById(id);
		return bookOpt.isPresent() ? bookOpt.get() : null;
	}
	
	public void save(Book book) {
		bookDao.save(book); 
	}

}
