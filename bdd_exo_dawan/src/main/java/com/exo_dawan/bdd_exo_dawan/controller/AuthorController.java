package com.exo_dawan.bdd_exo_dawan.controller;

import com.exo_dawan.bdd_exo_dawan.dto.AuthorDTO;
import com.exo_dawan.bdd_exo_dawan.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }


    @GetMapping("/{id}")
    public AuthorDTO getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    // Créer un nouvel auteur
    @PostMapping
    public List<AuthorDTO> createAuthor(@RequestBody List<AuthorDTO> author) {
        return authorService.createAuthor(author);
    }

    // Mettre à jour un auteur
    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO updatedAuthor) {
        return authorService.updateAuthor(id, updatedAuthor);
    }

    // Supprimer un auteur par l'ID
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
    }
}
