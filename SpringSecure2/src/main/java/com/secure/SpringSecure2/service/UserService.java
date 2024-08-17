package com.secure.SpringSecure2.service;

import com.secure.SpringSecure2.entity.User;
import com.secure.SpringSecure2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public User getUserById(int id){
        if(userRepo.findById(id).isPresent()){
            return userRepo.findById(id).get();
        }
        return null;
    }
    public User createUser(User user){
        return userRepo.save(user);
    }
    public String deleteUserById(int id){
        User user= getUserById(id);
        if(user!= null && !Objects.equals(user.getRole(), "ADMIN")){
            userRepo.delete(user);
            return "Success: User with id "+ id + " has been deleted successfully.";
        }
        return "Failure : User with id "+ id + " is either an ADMIN or not found";
    }
}
