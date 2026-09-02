package com.biolab.todolista.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Definindo getters and setters e construtores
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {

    //dados que login devolve
    private String username;

}