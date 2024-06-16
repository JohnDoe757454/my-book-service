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

	@SuppressWarnings("unchecked")
	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String author) {
		
		return em.createQuery(
				"select distinct p.publisherName from Publisher p "
				+ "left join Book b on p.publisherName=b.publisher.publisherName "
				+ "left join b.authors a on b.isbn=a.books.isbn where a.name=:authorsName"
				).setParameter("authorsName", author).getResultStream();
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
