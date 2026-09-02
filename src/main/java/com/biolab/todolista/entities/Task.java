package com.biolab.todolista.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Definindo entidade,getters and setters e construtores
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    //definifir id e geração do auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //definir campo titulo não pode ficar em branco de tamanho 1 a 100n chars
    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    //definir campo descrição não branco de tamanho de 1 a 500 chars
    @NotBlank
    @Size(min = 1, max = 500)
    private String description;

    //definir prioridade puxando de um enum que não pode ser nulo e vira um enum de string no banco
    @NotNull
    @Enumerated(EnumType.STRING)
    private Priority priority;

    //boleano para ver se a tarefa foi concluida ou não
    @NotNull
    private Boolean isChecked;

    //conexão com category
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

    //conexão com user
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

}
