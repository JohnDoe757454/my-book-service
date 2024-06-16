package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public String[] findPublishersByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Publisher> findById(String publisher) {
		return Optional.ofNullable(em.find(Publisher.class, publisher));
	}

	//@Transactional
	@Override
	public Publisher save(Publisher publisher) {
		em.persist(publisher);
		//em.merge(publisher);
		return publisher;
	}

}
