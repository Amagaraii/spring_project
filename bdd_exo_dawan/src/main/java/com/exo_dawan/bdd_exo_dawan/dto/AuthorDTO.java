package com.exo_dawan.bdd_exo_dawan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorDTO {

    private Long id;
    private String name;
    private int bookCount;  // Champ pour le nombre de livres

}
