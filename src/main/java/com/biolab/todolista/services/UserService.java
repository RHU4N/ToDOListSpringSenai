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

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserRes saveUser(UserReq userReq) {
        User user = new User();
        user.setUsername(userReq.getUsername());
        user.setPassword(userReq.getPassword());
        user.setEmail(userReq.getEmail());
        userRepository.save(user);
        UserRes userRes = new UserRes();
        userRes.setId(user.getId());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        return userRes;
    }
    
    public List<UserRes> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserRes> userRes = new ArrayList<>();
        for (User user : users) {
            UserRes userRes1 = new UserRes();
            userRes1.setId(user.getId());
            userRes1.setUsername(user.getUsername());
            userRes1.setEmail(user.getEmail());
            userRes.add(userRes1);
        }
        return userRes;
    }
    
    public UserRes getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow();
        UserRes userRes = new UserRes();
        userRes.setId(user.getId());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        return userRes;
    }
    
    public UserRes updateUser(long id,UserReq userReq) {
        User user = userRepository.findById(id).orElseThrow();
        user.setUsername(userReq.getUsername());
        user.setEmail(userReq.getEmail());
        user.setPassword(userReq.getPassword());
        userRepository.save(user);
        UserRes userRes = new UserRes();
        userRes.setId(user.getId());
        userRes.setUsername(user.getUsername());
        userRes.setEmail(user.getEmail());
        return userRes;
    }
    
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
    
    public String login(LoginReq loginReq) {
        if (userRepository.existsByUsernameAndPassword(loginReq.getUsername(), loginReq.getPassword())){
            return "success";
    }else {
            return "fail";
        }


    }
}
