package com.biolab.todolista.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Definindo getters and setters e construtores
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    //Atributos que podem ser mostrados
    private long id;
    private String username;
    private String email;
}
