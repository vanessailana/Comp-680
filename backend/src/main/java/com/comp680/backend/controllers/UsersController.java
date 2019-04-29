package com.comp680.backend.controllers;

import com.comp680.backend.repositories.*;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import com.comp680.backend.models.*;

import org.apache.commons.lang.UnhandledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//https://mysterious-harbor-56923.herokuapp.com
@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600 )
@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;
    
    @RequestMapping(value = "/profile/user", method = {RequestMethod.GET,RequestMethod.POST})
    User getUser(@RequestBody String email) 
    {
        User user = usersRepository.findByEmail(email);
        if(user==null)
        {
            User result = new User();
            result.setEmail(email);
            usersRepository.save(result);
            return result;
        }else{
            return user;
        }
    }


    @PatchMapping("/profile/user/info")
    User patchUser(@RequestBody User users) 
    {
        User find = usersRepository.findByEmail(users.getEmail());
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
        usersRepository.save(find);
        return find;
        
    }

    @PatchMapping("/profile/user/skills")
    User patchSkill(@RequestBody User user)
    {
        User find = usersRepository.findByEmail(user.getEmail());
        find.setSkills(user.getSkills());
        usersRepository.save(find);
        return find;
    }

    @PatchMapping("/profile/user/experiences")
    User patchExperience(@RequestBody User user)
    {
        User find = usersRepository.findByEmail(user.getEmail());
        find.setExperiences(user.getExperiences());
        usersRepository.save(find);
        return find;
    }

    @PatchMapping("/profile/user/educations")
    User patchEducation(@RequestBody User user)
    {
        User find = usersRepository.findByEmail(user.getEmail());
        find.setEducations(user.getEducations());
        usersRepository.save(find);
        return find;
    }

    @PatchMapping("/profile/user/projects")
    User patchProjects(@RequestBody User user)
    {
        User find = usersRepository.findByEmail(user.getEmail());
        find.setProjects(user.getProjects());
        usersRepository.save(find);
        return find;
    }

    @PatchMapping("/profile/user/socials")
    User patchSocials(@RequestBody User user)
    {
        User find = usersRepository.findByEmail(user.getEmail());
        find.setSocials(user.getSocials());
        usersRepository.save(find);
        return find;
    }

}