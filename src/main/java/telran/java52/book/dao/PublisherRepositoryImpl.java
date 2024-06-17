package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.book.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String author) {
		
		return em.createQuery(
				"select distinct p from Book b "
				+ "left join b.publisher p "
				+ "join b.authors a where a.name=?1", Publisher.class)
				.setParameter(1, author).getResultStream();
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
