package com.biolab.todolista.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//Definindo entidade,getters and setters e construtores
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    //definifir id e geração do auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Definindo descrição, não pode ser branco,valor unico de tamanho 1 a 50 chars
    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 50)
    private String description;

    //Gerando "uma lista de tasks" para representar a ligação 1..n no banco
    @OneToMany(mappedBy = "category")
    private List<Task> tasks = new ArrayList<>();
}
