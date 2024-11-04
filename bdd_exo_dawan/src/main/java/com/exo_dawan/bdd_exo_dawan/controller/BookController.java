package com.exo_dawan.bdd_exo_dawan.controller;

import com.exo_dawan.bdd_exo_dawan.dto.BookDTO;
import com.exo_dawan.bdd_exo_dawan.model.Book;
import com.exo_dawan.bdd_exo_dawan.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Récupérer tous les livres
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Récupérer un livre par son ID
    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Créer un nouveau livre
    @PostMapping
    public BookDTO createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    //Crée plusieurs livres
    @PostMapping("/bundle")
    public List<BookDTO> createBooks(@RequestBody List<Book> books) {
        return bookService.createBooks(books);
    }

    // Mettre à jour un livre
    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    // Supprimer un livre
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
