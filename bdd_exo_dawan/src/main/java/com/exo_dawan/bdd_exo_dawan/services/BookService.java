package com.exo_dawan.bdd_exo_dawan.services;

import com.exo_dawan.bdd_exo_dawan.dto.BookDTO;
import com.exo_dawan.bdd_exo_dawan.model.Author;
import com.exo_dawan.bdd_exo_dawan.model.Book;
import com.exo_dawan.bdd_exo_dawan.repository.AuthorRepository;
import com.exo_dawan.bdd_exo_dawan.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // GET tous les livres sous forme de DTO
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getId()))
                .collect(Collectors.toList());
    }

    // GET un livre par son ID
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthor().getId()))
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // CREATE un nouveau livre
    public BookDTO createBook(Book book) {
        // Vérifie que l'auteur existe
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        return new BookDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor().getId());
    }

    //CREATE plusieurs livre
    public List<BookDTO> createBooks(List<Book> books) {
        return books.stream().map(book -> {
            Author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(author);  // Associe chaque livre à l'auteur
            Book savedBook = bookRepository.save(book);  // Sauvegarde le livre
            return new BookDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor().getId());
        }).collect(Collectors.toList());
    }

    // PUT à jour un livre
    public BookDTO updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            Author author = authorRepository.findById(updatedBook.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(author);
            Book savedBook = bookRepository.save(book);
            return new BookDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor().getId());
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // DELETE un livre
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
