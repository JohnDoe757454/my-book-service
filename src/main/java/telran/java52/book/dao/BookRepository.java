package telran.java52.book.dao;

import java.util.Optional;

import telran.java52.book.model.Book;


public interface BookRepository {


	void deleteByAuthorsName(String name);

	Optional<Book> findById(String isbn);

	void delete(Book book);

	Book save(Book book);

	boolean existsById(String isbn);



}
