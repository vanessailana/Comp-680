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

    private final UsersRepository usersRepository;

    private final SocialsRepository socialsRepository;

    private final EducationsRepository educationsRepository;

    private final ExperiencesRepository experiencesRepository;

    private final SkillRepository skillRepository;

    private final ProjectsRepository projectRepository;

    UsersController(
        UsersRepository usersRepository,
        SocialsRepository socialsRepository, 
        EducationsRepository educationsRepository,
        ExperiencesRepository experiencesRepository,
        SkillRepository skillRepository,
        ProjectsRepository projectRepository
    ){
        this.usersRepository = usersRepository;
        this.socialsRepository = socialsRepository;
        this.educationsRepository = educationsRepository;
        this.experiencesRepository = experiencesRepository;
        this.skillRepository = skillRepository;
        this.projectRepository = projectRepository;
    }


    @RequestMapping(value = "/profile/user", method = {RequestMethod.GET,RequestMethod.POST})
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


        Social social = socialsRepository.findByUserId(id);
        
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
        System.out.println("ID: "+social.getUser().getId());
        Social find = socialsRepository.findByUserId(social.getUser().getId());
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
        return educationsRepository.findByUserId(id);
    }

    @PostMapping("/profile/edu/post")
    List<Education> postEducation(@RequestBody Education edu)
    {
        educationsRepository.save(edu);

        return educationsRepository.findByUserId(edu.getUser().getId());
    }

    @RequestMapping(value = "/profile/patchEdu")
    List<Education> patchEducation(@RequestBody Education edu)
    {

        Education find = educationsRepository.findById(edu.getId());
        find.setMajor(edu.getMajor());
        find.setSchoolName(edu.getSchoolName());
        find.setDegree(edu.getDegree());
        find.setDescription(edu.getDescription());
        find.setEndDate(edu.getEndDate());
        find.setStartDate(edu.getStartDate());
        find.setInProgress(edu.getInProgress());
        educationsRepository.save(find);
        return educationsRepository.findByUserId(edu.getUser().getId());
    }

    @DeleteMapping("/profile/edu/delete/{users_id}/{edu_id}")
    List<Education> deleteEducation(@PathVariable long users_id, @PathVariable long edu_id)
    {
        Education find = educationsRepository.findById(edu_id);

        educationsRepository.delete(find);

        return educationsRepository.findByUserId(users_id);
        
    }

    @GetMapping("/profile/experience/{id}")
    List<Experience> getExperience(@PathVariable("id") long id)
    {
        System.out.println("Get");
        return experiencesRepository.findByUserId(id);
    }


    @PostMapping("/profile/experience/post")
    List<Experience> postExperience(@RequestBody Experience exp)
    {
        experiencesRepository.save(exp);

        return experiencesRepository.findByUserId(exp.getUser().getId());
    }

    @RequestMapping(value = "/profile/patchExp")
    List<Experience> patchExperience(@RequestBody Experience exp)
    {

        Experience find = experiencesRepository.findById(exp.getId());
    
        find.setCompany(exp.getCompany());
        find.setTitle(exp.getTitle());
        find.setCurrent(exp.getCurrent());
        find.setEndDate(exp.getEndDate());
        find.setStartDate(exp.getStartDate());
        find.setDescription(exp.getDescription());
        experiencesRepository.save(find);
        return experiencesRepository.findByUserId(exp.getUser().getId());
    }

    @DeleteMapping("/profile/experience/delete/{users_id}/{exp_id}")
    List<Experience> deleteExperience(@PathVariable long users_id, @PathVariable long exp_id)
    {
        Experience find = experiencesRepository.findById(exp_id);

        experiencesRepository.delete(find);

        return experiencesRepository.findByUserId(users_id);
        
    }


    @GetMapping("/profile/skill/{id}")
    List<Skill> getSkill(@PathVariable("id") long id)
    {
        System.out.println("Get");
        return skillRepository.findByUserId(id);
    }


    @PostMapping("/profile/skill/post")
    List<Skill> postSkill(@RequestBody Skill skill)
    {
        skillRepository.save(skill);

        return skillRepository.findByUserId(skill.getUser().getId());
    }

    @RequestMapping(value = "/profile/skill/patch")
    List<Skill> patchSkill(@RequestBody Skill skill)
    {
        Skill find = skillRepository.findById(skill.getId());

        find.setSkill(skill.getSkill());
        find.setLevel(skill.getLevel());

        return skillRepository.findByUserId(skill.getUser().getId());
    }

    @DeleteMapping("/profile/skill/delete/{users_id}/{skill_id}")
    List<Skill> deleteSkill(@PathVariable long users_id, @PathVariable long skill_id)
    {
        Skill find = skillRepository.findById(skill_id);

        skillRepository.delete(find);

        return skillRepository.findByUserId(users_id);
        
    }


    @GetMapping("/profile/project/{id}")
    List<Project> getProject(@PathVariable("id") long id)
    {
        System.out.println("Get");
        return projectRepository.findByUserId(id);
    }


    @RequestMapping(value = "/profile/project/post", method = {RequestMethod.POST,RequestMethod.OPTIONS})
    List<Project> postProject(@RequestBody Project project)
    {
        projectRepository.save(project);

        return projectRepository.findByUserId(project.getUser().getId());
    }

    @RequestMapping(value = "/profile/project/patch")
    List<Project> patchProject(@RequestBody Project project)
    {
        Project find = projectRepository.findById(project.getId());

        find.setProjectName(project.getProjectName());
        find.setDescription(project.getDescription());
        find.setTechnologies(project.getTechnologies());
        find.setLinks(project.getLink());
        find.setStartDate(project.getStartDate());
        find.setEndDate(project.getEndDate());
        
        return projectRepository.findByUserId(project.getUser().getId());
    }

    @DeleteMapping("/profile/project/delete/{users_id}/{project_id}")
    List<Project> deleteProject(@PathVariable long users_id, @PathVariable long project_id)
    {
        Project find = projectRepository.findById(project_id);

        projectRepository.delete(find);

        return projectRepository.findByUserId(users_id);
        
    }

}