package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import telran.java52.model.Book;


public interface BookRepository {

	Stream<Book> findBooksByAuthorsName(String name);

	Stream<Book> findBooksByPublisherPublisherName(String publisherName);
	
	void deleteByAuthorsName(String name);

	Optional<Book> findById(String isbn);

	void delete(Book book);

	Book save(Book book);

	boolean existsById(String isbn);



}
