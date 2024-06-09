package telran.java52.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java52.model.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {

}
