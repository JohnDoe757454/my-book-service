package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.model.Book;

@Repository
public class BookRepositoryImpl implements BookRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Book> findBooksByAuthorsName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Book> findBooksByPublisherPublisherName(String publisherName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByAuthorsName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Book> findById(String isbn) {
		return Optional.ofNullable(em.find(Book.class, isbn));
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public Book save(Book book) {
		em.persist(book);
		return book;
	}

	@Override
	public boolean existsById(String isbn) {
		
		return em.find(Book.class, isbn) != null;
	}

}
