package com.biolab.todolista.DTOs.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Definindo getters and setters e construtores
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatRes {
    //atributos que são possiveis puxar em resposta
    private long id;
    private String desc;
}
