package com.comp680.backend.controllers;

import com.comp680.backend.repositories.EducationsRepository;
import com.comp680.backend.repositories.ExperiencesRepository;
import com.comp680.backend.repositories.ProjectsRepository;
import com.comp680.backend.repositories.SkillRepository;
import com.comp680.backend.repositories.SocialsRepository;
import com.comp680.backend.repositories.UsersRepository;

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
@CrossOrigin
@RestController
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EducationsRepository eduRepository;

    @Autowired
    private ExperiencesRepository expRepository;

    @Autowired
    private ProjectsRepository proRepository;

    @Autowired
    private SocialsRepository socialRepository;

    @Autowired
    private SkillRepository skillsRepository;


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

 
    @PostMapping("/profile/patch")
    User patchUser(@RequestBody User user)
    {
        usersRepository.save(user);
        return usersRepository.findById(user.getId()).get();
    }

    @GetMapping("/profile/edu/{id}")
    List<Education> getEducation(@PathVariable long id)
    {
        return eduRepository.findByUserId(id);
    }

    @GetMapping("/profile/exp/{id}")
    List<Education> getExp(@PathVariable long id)
    {
        return eduRepository.findByUserId(id);
    }


    @GetMapping("/profile/pro/{id}")
    List<Project> getPro(@PathVariable long id)
    {
        return proRepository.findByUserId(id);
    }

    @GetMapping("/profile/skill/{id}")
    List<Skill> getSkills(@PathVariable long id)
    {
        return skillsRepository.findByUserId(id);
    }

    @GetMapping("/profile/social/{id}")
    Social getSocial(@PathVariable long id)
    {
        return socialRepository.findByUserId(id);
    }

    @PostMapping("/profile/edu/{id}")
    List<Education> postEducation(@RequestBody List<Education> educations,@PathVariable long id){
        
        User user = usersRepository.findById(id);
        educations.stream().forEach(
            elm->{
                elm.setUser(user);
            }
        );

        eduRepository.saveAll(educations);
        return educations;
    }

    @PostMapping("/profile/pro/{id}")
    List<Project> postProjects(@RequestBody List<Project> projects,@PathVariable long id){
        User user = usersRepository.findById(id);
        projects.stream().forEach(
            elm->{
                elm.setUser(user);
            }
        );

        proRepository.saveAll(projects);
        return projects;
    }

    @PostMapping("/profile/exp/{id}")
    List<Experience> postExp(@RequestBody List<Experience> exp,@PathVariable long id){
        User user = usersRepository.findById(id);
        exp.stream().forEach(
            elm->{
                elm.setUser(user);
            }
        );

        expRepository.saveAll(exp);
        return exp;
    }

    @PostMapping("/profile/skills/{id}")
    List<Skill> postSkills(@RequestBody List<Skill> skills,@PathVariable long id){
        User user = usersRepository.findById(id);
        skills.stream().forEach(
            elm->{
                elm.setUser(user);
            }
        );

        skillsRepository.saveAll(skills);
        return skills;
    }

    @PostMapping("/profile/social/{id}")
    Social postSocial(@RequestBody Social social,@PathVariable long id){
        User user = usersRepository.findById(id);
        
        social.setUser(user);

        socialRepository.save(social);
        return social;
    }


}