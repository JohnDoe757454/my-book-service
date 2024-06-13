package telran.java52.book.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.model.Author;
import telran.java52.model.Publisher;

public interface AuthorRepository {

	Optional<Author> findById(String name);

	Author save(Author author);

	void delete(Author author);

}
