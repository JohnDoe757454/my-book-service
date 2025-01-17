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
				.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
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

	@Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByAuthor(String author) {
		return bookRepository.findBooksByAuthorsName(author).map(b -> modelMapper.map(b, BookDto.class))
				.toArray(BookDto[]::new);
	}

	@Transactional(readOnly = true)
	@Override
	public BookDto[] findBooksByPublisher(String publisher) {
		return bookRepository.findBooksByPublisherPublisherName(publisher).map(b -> modelMapper.map(b, BookDto.class))
				.toArray(BookDto[]::new);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public AuthorsDto removeAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

}
