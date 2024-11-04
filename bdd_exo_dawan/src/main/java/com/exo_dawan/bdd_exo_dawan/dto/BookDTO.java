package com.exo_dawan.bdd_exo_dawan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDTO {

    private Long id;
    private String title;
    private Long authorId;  // On ne renvoie que l'ID de l'auteur


}
