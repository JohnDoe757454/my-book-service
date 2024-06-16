package telran.java52.book.dao;

import java.util.Optional;

import telran.java52.model.Author;

public interface AuthorRepository {

	Optional<Author> findById(String name);

	Author save(Author author);

	void delete(Author author);

}
