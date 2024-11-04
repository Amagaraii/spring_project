package com.exo_dawan.bdd_exo_dawan.repository;

import com.exo_dawan.bdd_exo_dawan.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
