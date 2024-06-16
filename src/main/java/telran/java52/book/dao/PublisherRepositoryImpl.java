package telran.java52.book.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import telran.java52.model.Publisher;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Stream<Publisher> findDistinctByBooksAuthorsName(String author) {
		
		return em.createQuery(
				"select distinct p.publisher_name from publisher p "
				+ "left join book b on p.publisher_name=b.publisher_publisher_name "
				+ "left join book_authors a on b.isbn=a.book_isbn where a.authors_name=:authorsName"
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
