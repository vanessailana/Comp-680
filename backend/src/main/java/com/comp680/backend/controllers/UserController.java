package com.comp680.backend.controllers;
import com.comp680.backend.models.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import com.comp680.backend.models.Education;
import com.comp680.backend.models.EducationRepository;
import com.comp680.backend.models.Experience;
import com.comp680.backend.models.ExperienceRepository;
import com.comp680.backend.models.Social;
import com.comp680.backend.models.SocialRepository;
import com.comp680.backend.models.Users;

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


<<<<<<< Updated upstream
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*")
=======

>>>>>>> Stashed changes
@RestController
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
public class UserController {

    private final UsersRepository repository;

    private final SocialRepository srep;

    private final EducationRepository erep;

    private final ExperienceRepository exrep;

    UserController(UsersRepository rep, SocialRepository srep, 
                EducationRepository erep, ExperienceRepository exrep)
    {
        this.repository = rep;
        this.srep = srep;
        this.erep = erep;
        this.exrep = exrep;
    }


    @PostMapping("/profile/user")
    Users createOrGetUser(@RequestBody String email) 
    {
        Users users = repository.findByEmail(email);
        if(users==null)
        {
            Users newUser = new Users();

            newUser.setEmail(email);
            repository.save(newUser);

            System.out.println("Email: "+email);
            return newUser;
        }
        return users;
    }



    @PostMapping("/profile/create")
    Users patchUser(@RequestBody Users users) 
    {
        Users find = repository.findByEmail(users.getEmail());
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

    @RequestMapping(value = "/profile/social", method = {RequestMethod.GET,RequestMethod.POST})
    Social createOrSocialInfo(@RequestBody long id)
    {

        Social social = srep.findByUsersId(id);
        if(social == null)
        {
            Social newSocial = new Social();

            newSocial.setUsers(repository.findById(id));

            srep.save(newSocial);

            return newSocial;
        }
        return social;
        
    }

    @PostMapping("/profile/social/patch")
    Social patchSocial(@RequestBody Social social)
    {
        Social find = srep.findByUsersId(social.getUsers().getId());
        find.setFacebook(social.getFacebook());
        find.setTwitter(social.getTwitter());
        find.setGithub(social.getGithub());
        find.setWebsite(social.getWebsite());
        find.setLinkedin(social.getLinkedin());
        srep.save(find);
        return find;
    }

    @GetMapping("/profile/edu/{id}")
    List<Education> getEducation(@PathVariable("id") long id)
    {
        System.out.println("Get");
        return erep.findByUsersId(id);
    }

    @PostMapping("/profile/edu/post")
    List<Education> postEducation(@RequestBody Education edu)
    {
        erep.save(edu);

        return erep.findByUsersId(edu.getUsers().getId());
    }

    @PatchMapping("/profile/edu/patch")
    List<Education> patchEducation(@RequestBody Education edu)
    {

        Education find = erep.findById(edu.getId());
        find.setMajor(edu.getMajor());
        find.setSchool(edu.getSchool());
        find.setDegree(edu.getDegree());
        find.setDescription(edu.getDescription());
        find.setEnd(edu.getEnd());
        find.setStart(edu.getStart());
        find.setInProgress(edu.getInProgress());
        erep.save(find);
        return erep.findByUsersId(edu.getUsers().getId());
    }

    @DeleteMapping("/profile/edu/delete/{users_id}/{edu_id}")
    List<Education> deleteEducation(@PathVariable long users_id, @PathVariable long edu_id)
    {
        Education find = erep.findById(edu_id);

        erep.delete(find);

        return erep.findByUsersId(users_id);
        
    }

    @GetMapping("/profile/experience/{id}")
    List<Experience> getExperience(@PathVariable("id") long id)
    {
        System.out.println("Get");
        return exrep.findByUsersId(id);
    }


    @PostMapping("/profile/experience/post")
    List<Experience> postExperience(@RequestBody Experience exp)
    {
        exrep.save(exp);

        return exrep.findByUsersId(exp.getUsers().getId());
    }

    @PatchMapping("/profile/experience/patch")
    List<Experience> patchExperience(@RequestBody Experience exp)
    {

        Experience find = exrep.findById(exp.getId());
    
        find.setCompany(exp.getCompany());
        find.setTitle(exp.getTitle());
        find.setCurrent(exp.getCurrent());
        find.setEndDate(exp.getEndDate());
        find.setStartDate(exp.getStartDate());
        find.setDescription(exp.getDescription());
        exrep.save(find);
        return exrep.findByUsersId(exp.getUsers().getId());
    }

    @DeleteMapping("/profile/experience/delete/{users_id}/{exp_id}")
    List<Experience> deleteExperience(@PathVariable long users_id, @PathVariable long exp_id)
    {
        Experience find = exrep.findById(exp_id);

        exrep.delete(find);

        return exrep.findByUsersId(users_id);
        
    }



    
    

}