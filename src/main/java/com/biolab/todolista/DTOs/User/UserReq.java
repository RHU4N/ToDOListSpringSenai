package com.biolab.todolista.DTOs.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Definindo getters and setters e construtores
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    //Atributos que são enviados para user não são auto gerados ou padronizados
    private String username;
    private String email;
    private String password;

}
