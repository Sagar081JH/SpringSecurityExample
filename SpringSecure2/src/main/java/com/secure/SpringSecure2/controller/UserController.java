package com.secure.SpringSecure2.controller;

import com.secure.SpringSecure2.entity.User;
import com.secure.SpringSecure2.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<?> getAllUsers(HttpServletResponse response){
        setDummyCookies(response);
        try{
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        if(userService.getUserById(id) != null){
            return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user){
            return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteUserById(@PathVariable int id){
        String response = userService.deleteUserById(id);
        if(response.contains("success")){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    public void setDummyCookies(HttpServletResponse response){
        Cookie cookie = new Cookie("dummyCookie", "dummyCookie");
        cookie.setMaxAge(259200);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

