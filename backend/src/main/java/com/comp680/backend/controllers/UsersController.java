package com.comp680.backend.controllers;

import com.comp680.backend.repositories.EducationsRepository;
import com.comp680.backend.repositories.ExperiencesRepository;
import com.comp680.backend.repositories.SocialsRepository;
import com.comp680.backend.repositories.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import com.comp680.backend.models.User;
import com.comp680.backend.models.Education;
import com.comp680.backend.models.Experience;
import com.comp680.backend.models.Social;
import com.comp680.backend.models.User;

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

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "*", maxAge = 3600)
@RestController
public class UsersController {

    private final UsersRepository usersRepository;

    private final SocialsRepository socialsRepository;

    private final EducationsRepository educationsRepository;

    private final ExperiencesRepository experiencesRepository;

    UsersController(
        UsersRepository usersRepository,
        SocialsRepository socialsRepository, 
        EducationsRepository educationsRepository,
        ExperiencesRepository experiencesRepository
    ){
        this.usersRepository = usersRepository;
        this.socialsRepository = socialsRepository;
        this.educationsRepository = educationsRepository;
        this.experiencesRepository = experiencesRepository;
    }


    @PostMapping("/profile/user")
    User createOrGetUser(@RequestBody String email) 
    {
        User users = usersRepository.findByEmail(email);
        if(users==null)
        {
            User newUser = new User();

            newUser.setEmail(email);
            usersRepository.save(newUser);

            System.out.println("Email: "+email);
            return newUser;
        }
        return users;
    }


    @PostMapping("/profile/create")
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

    @RequestMapping(value = "/profile/social", method = {RequestMethod.GET,RequestMethod.POST})
    Social createOrSocialInfo(@RequestBody long id){

        Social social = socialsRepository.findByUsersId(id);
        if(social == null)
        {
            Social newSocial = new Social();

            newSocial.setUser(usersRepository.findById(id));

            socialsRepository.save(newSocial);

            return newSocial;
        }
        return social;
        
    }

    @PostMapping("/profile/social/patch")
    Social patchSocial(@RequestBody Social social)
    {
        Social find = socialsRepository.findByUsersId(social.getUser().getId());
        find.setFacebook(social.getFacebook());
        find.setTwitter(social.getTwitter());
        find.setGithub(social.getGithub());
        find.setWebsite(social.getWebsite());
        find.setLinkedin(social.getLinkedin());
        socialsRepository.save(find);
        return find;
    }

    @GetMapping("/profile/edu/{id}")
    List<Education> getEducation(@PathVariable("id") long id)
    {
        System.out.println("Get");
        return educationsRepository.findByUsersId(id);
    }

    @PostMapping("/profile/edu/post")
    List<Education> postEducation(@RequestBody Education edu)
    {
        educationsRepository.save(edu);

        return educationsRepository.findByUsersId(edu.getUser().getId());
    }

    @PatchMapping("/profile/edu/patch")
    List<Education> patchEducation(@RequestBody Education edu)
    {

        Education find = educationsRepository.findById(edu.getUser().getId());
        find.setMajor(edu.getMajor());
        find.setSchoolName(edu.getSchoolName());
        find.setDegree(edu.getDegree());
        find.setDescription(edu.getDescription());
        find.setEndDate(edu.getEndDate());
        find.setStartDate(edu.getStartDate());
        find.setInProgress(edu.getInProgress());
        educationsRepository.save(find);
        return educationsRepository.findByUsersId(edu.getUser().getId());
    }

    @DeleteMapping("/profile/edu/delete/{users_id}/{edu_id}")
    List<Education> deleteEducation(@PathVariable long users_id, @PathVariable long edu_id)
    {
        Education find = educationsRepository.findById(edu_id);

        educationsRepository.delete(find);

        return educationsRepository.findByUsersId(users_id);
        
    }

    @GetMapping("/profile/experience/{id}")
    List<Experience> getExperience(@PathVariable("id") long id)
    {
        System.out.println("Get");
        return experiencesRepository.findByUsersId(id);
    }


    @PostMapping("/profile/experience/post")
    List<Experience> postExperience(@RequestBody Experience exp)
    {
        experiencesRepository.save(exp);

        return experiencesRepository.findByUsersId(exp.getUser().getId());
    }

    @PatchMapping("/profile/experience/patch")
    List<Experience> patchExperience(@RequestBody Experience exp)
    {

        Experience find = experiencesRepository.findById(exp.getUser().getId());
    
        find.setCompany(exp.getCompany());
        find.setTitle(exp.getTitle());
        find.setCurrent(exp.getCurrent());
        find.setEndDate(exp.getEndDate());
        find.setStartDate(exp.getStartDate());
        find.setDescription(exp.getDescription());
        experiencesRepository.save(find);
        return experiencesRepository.findByUsersId(exp.getUser().getId());
    }

    @DeleteMapping("/profile/experience/delete/{users_id}/{exp_id}")
    List<Experience> deleteExperience(@PathVariable long users_id, @PathVariable long exp_id)
    {
        Experience find = experiencesRepository.findById(exp_id);

        experiencesRepository.delete(find);

        return experiencesRepository.findByUsersId(users_id);
        
    }

}