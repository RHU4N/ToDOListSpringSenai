package com.biolab.todolista.DTOs.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Definindo getters and setters e construtores
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatReq {
    //unico atributo que precisa ser enviado em request
    private String desc;
}
