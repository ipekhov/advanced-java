package my.learning.springweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import my.learning.springweb.entities.Book;
import my.learning.springweb.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public List<Book> books() {
		return bookService.findAll();
	}
	
	@PostMapping("/books")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createBook(@RequestBody Book book) {
		bookService.save(book);
	}

	@GetMapping("/books/{id}")
	public Book findBookById(@PathVariable("id") Long id) {
		return bookService.findById(id);
	}
}
