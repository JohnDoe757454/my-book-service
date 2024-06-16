package telran.java52.book.service;

import telran.java52.book.dto.AuthorsDto;
import telran.java52.book.dto.BookDto;

public interface BookService {

	Boolean addBook(BookDto bookDto);
	
	BookDto findBookByIsbn(String isbn);
	
	BookDto removeBookByIsbn(String isbn);
	
	BookDto updateBookTitle(String isbn, String title);
	
	BookDto[] findBooksByAuthor(String author);
	
	BookDto[] findBooksByPublisher(String publisher);
	
	AuthorsDto[] findBookAuthor(String isbn);
	
	String[] findPublishersByAuthor(String author);
	
	AuthorsDto removeAuthor(String author);
}
