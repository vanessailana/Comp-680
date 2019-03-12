package com.comp680.backend.controllers;
import com.comp680.backend.repositories.UsersRepository;


import com.comp680.backend.models.User;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class UserController {

    private final UsersRepository repository;

    UserController(UsersRepository rep)
    {
        this.repository = rep;
    }


    @PostMapping("/profile/user")
    User createOrGetUser(@RequestBody String email) 
    {
        User users = repository.findByEmail(email);
        if(users==null)
        {
            User newUser = new User();

            newUser.setEmail(email);
            repository.save(newUser);

            System.out.println("Email: "+email);
            return newUser;
        }
        return users;
    }



    @PostMapping("/profile/create")
    User patchUser(@RequestBody User users) 
    {
        User find = repository.findByEmail(users.getEmail());
        find.setAddress(users.getAddress());
        find.setCity(users.getCity());
        //find.setEmail(users.getEmail());
        find.setFirstName(users.getFirstName());
        find.setLastName(users.getLastName());
        find.setNumber(users.getNumber());
        find.setObjective(users.getObjective());
        find.setResume(users.getResume());
        find.setState(users.getState());
        find.setZipCode(users.getZipCode());
        repository.save(find);
        return find;
        
    }
    
    

}