package telran.java52.book.dao;


import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;

import telran.java52.model.Publisher;

public interface PublisherRepository  {

	Stream<Publisher> findDistinctByBooksAuthorsName(String author);

	Optional<Publisher> findById(String publisher);

	Publisher save(Publisher publisher);
}
