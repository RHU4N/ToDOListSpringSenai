package com.biolab.todolista.services;

import com.biolab.todolista.DTOs.User.LoginReq;
import com.biolab.todolista.DTOs.User.LoginRes;
import com.biolab.todolista.DTOs.User.UserReq;
import com.biolab.todolista.DTOs.User.UserRes;
import com.biolab.todolista.entities.User;
import com.biolab.todolista.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//definindo service
@Service
public class UserService {
    //puxando repository para inversão de dependencia
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //salvando usuario retornado userResponse e enviando UserRequest
    public UserRes saveUser(UserReq userReq) {
        User user = new User(); //cria user com dados do request
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setEmail(userReq.getEmail());
        userRepository.save(user);//salva em banco
        UserRes userRes = new UserRes();//cria response para return
        userRes.setId(user.getId());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        return userRes;
    }

    //função para retornar lista com todos os users
    public List<UserRes> getAllUsers() {
        //pega o dado de todos os users e coloca na lista de user
        List<User> users = userRepository.findAll();
        List<UserRes> userRes = new ArrayList<>();//faz lista de user response
        for (User user : users) { //enche lista response com os dados de lista user pra return
            UserRes userRes1 = new UserRes();
            userRes1.setId(user.getId());
            userRes1.setUsername(user.getUsername());
            userRes1.setEmail(user.getEmail());
            userRes.add(userRes1);
        }
        return userRes;
    }

    //Função para retorna um user Response vindo de id
    public UserRes getUserById(long id) {
        //pega o dado de id
        User user = userRepository.findById(id).orElseThrow();
        UserRes userRes = new UserRes();//converte para user response para return
        userRes.setId(user.getId());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        return userRes;
    }

    //Função para alterar pegando id e request e devolvendo response
    public UserRes updateUser(long id,UserReq userReq) {
        //pega dado e altera valores
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userReq.getUsername());
        user.setEmail(userReq.getEmail());
        user.setPassword(userReq.getPassword());
        userRepository.save(user);  // salva no banco
        UserRes userRes = new UserRes();//converte para retunr
        userRes.setId(user.getId());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        return userRes;
    }

    //deleta pelo id
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    //emula login a partir do DTO e do metodo que retorna true e false para existencia de atributos me banco
    public String login(LoginReq loginReq) {
        if (userRepository.existsByUsernameAndPassword(loginReq.getUsername(), loginReq.getPassword())){
            return "success";
    }else {
            return "fail";
        }


    }
}
