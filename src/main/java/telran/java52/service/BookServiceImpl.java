package telran.java52.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.book.dao.AuthorRepository;
import telran.java52.book.dao.BookRepository;
import telran.java52.book.dao.PublisherRepository;
import telran.java52.book.dto.AuthorsDto;
import telran.java52.book.dto.BookDto;
import telran.java52.book.dto.exception.EntityNotFound;
import telran.java52.model.Author;
import telran.java52.model.Book;
import telran.java52.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;

	@Override
	@Transactional
	public Boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElseGet(() -> publisherRepository.save(new Publisher(bookDto.getPublisher())));
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElseGet(() -> authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());

		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFound::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto removeBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFound::new);
		bookRepository.delete(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Transactional
	@Override
	public BookDto updateBookTitle(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFound::new);
		book.setTitle(title);
		return modelMapper.map(book, BookDto.class);
	}

	// @Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFound::new);
		// return bookRepository.findBooksByAuthorsName(author).map(b ->
		// modelMapper.map(b, BookDto.class))
		// .toArray(BookDto[]::new);
		return author.getBooks().stream().map(b -> modelMapper.map(b, BookDto.class)).toArray(BookDto[]::new);
	}

	// @Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByPublisher(String publisherName) {
		Publisher publisher = publisherRepository.findById(publisherName).orElseThrow(EntityNotFound::new);
//		return bookRepository.findBooksByPublisherPublisherName(publisher).map(b -> modelMapper.map(b, BookDto.class))
//				.toArray(BookDto[]::new);
		return publisher.getBooks().stream().map(b -> modelMapper.map(b, BookDto.class)).toArray(BookDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public AuthorsDto[] findBookAuthor(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFound::new);
		return book.getAuthors().stream().map(a -> modelMapper.map(a, AuthorsDto.class)).toArray(AuthorsDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public String[] findPublishersByAuthor(String author) {
		// return publisherRepository.findPublishersByAuthor(author);
		return publisherRepository.findDistinctByBooksAuthorsName(author).map(Publisher::getPublisherName)
				.toArray(String[]::new);
	}

	@Transactional
	@Override
	public AuthorsDto removeAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElseThrow(EntityNotFound::new);
		// bookRepository.findBooksByAuthorsName(authorName).forEach(b->
		// bookRepository.delete(b));
		bookRepository.deleteByAuthorsName(authorName);
		authorRepository.delete(author);
		return modelMapper.map(author, AuthorsDto.class);
	}

}
