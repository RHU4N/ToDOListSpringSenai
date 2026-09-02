package com.biolab.todolista.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class User {
    //definifir id e geração do auto_increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Definir campo username que não pode ficar em branco
    @NotBlank
    private String username;

    //Definir campo senha de tamanho de 6 a 20 não pode ficar em branco
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    //defini campo email não pode ficar em branco e segue padfrão email
    @NotBlank
    @Email
    private String email;

    //gerar "Lista de tasks" para conexaão oneToMaby com task
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

}
