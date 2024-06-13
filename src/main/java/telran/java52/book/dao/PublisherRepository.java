package telran.java52.book.dao;


import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;

import telran.java52.model.Publisher;

public interface PublisherRepository  {

	@Query("select distinct p.publisherName from Book b join b.publisher p join b.authors a where a.name=?1")
	String[] findPublishersByAuthor(String author);
	
	Stream<Publisher> findDistinctByBooksAuthorsName(String author);

	Optional<Publisher> findById(String publisher);

	Publisher save(Publisher publisher);
}
