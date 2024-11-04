package com.exo_dawan.bdd_exo_dawan.services;

import com.exo_dawan.bdd_exo_dawan.dto.AuthorDTO;
import com.exo_dawan.bdd_exo_dawan.model.Author;
import com.exo_dawan.bdd_exo_dawan.repository.AuthorRepository;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    //GET ALL Auteur
    public List<AuthorDTO> getAllAuthors() {
        List<Author> result = authorRepository.findAll();
        return result.stream().map(author -> new AuthorDTO(author.getId(), author.getName(), author.getBooks().size())).collect(Collectors.toList());
    }

    //CREATE un auteur
    public List<AuthorDTO> createAuthor(List<AuthorDTO> author) {
        List<Author> authRes = new ArrayList<>();

        List<Author> authors = author.stream().map(auth -> new Author(auth.getId(), auth.getName(), new ArrayList<>())).toList();
        authors.forEach(auth -> authRes.add(authorRepository.save(auth)));

        return authRes.stream().map(auth -> new AuthorDTO(auth.getId(), auth.getName(), auth.getBooks().size())).collect(Collectors.toList());
    }

    //GET un auteur par id
    public AuthorDTO getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new AuthorDTO(author.getId(), author.getName(), author.getBooks().size()))
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    // DELETE by id un auteur
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    // PUT à jour un auteur
    public AuthorDTO updateAuthor(Long id, AuthorDTO updatedAuthor) {
        return authorRepository.findById(id).map(author -> {
            author.setName(updatedAuthor.getName());
            Author savedAuthor = authorRepository.save(author);
            return new AuthorDTO(savedAuthor.getId(), savedAuthor.getName(), savedAuthor.getBooks().size());
        }).orElseThrow(() -> new RuntimeException("Author not found"));
    }
}
