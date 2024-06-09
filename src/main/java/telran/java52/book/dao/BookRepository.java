package telran.java52.book.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

	Stream<Book> findBooksByAuthorsName(String name);

	Stream<Book> findBooksByPublisherPublisherName(String publisherName);



}
