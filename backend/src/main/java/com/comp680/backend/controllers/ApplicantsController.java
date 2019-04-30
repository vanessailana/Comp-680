package com.comp680.backend.controllers;

import com.comp680.backend.repositories.ApplicantsRepository;
import com.comp680.backend.repositories.AnswerRepository;
import com.comp680.backend.repositories.JobsRepository;
import com.comp680.backend.repositories.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Applicant;
import com.comp680.backend.models.Job;
import com.comp680.backend.models.Answer;
import com.comp680.backend.models.User;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

//https://mysterious-harbor-56923.herokuapp.com
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class ApplicantsController {

    @Autowired
    ApplicantsRepository applicantsRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JobsRepository jobsRepository;

    @PostMapping("/apply")
    public boolean apply(@RequestBody Applicant applicant) {
        JSONObject obj = new JSONObject();
        Job job = jobsRepository.findById(applicant.getJob().getId()).get();
        User user = usersRepository.findById(applicant.getUser().getId()).get();
        Applicant store = new Applicant();
        store.setUser(user);
        store.setJob(job);
        applicantsRepository.save(store);
        return true;
    }

    @PostMapping("/answers")
    public boolean answers(@RequestBody List<Answer> answers) {
        answerRepository.saveAll(answers);
        return true;
    }

    @GetMapping("/answers/{job_id}/{user_id}")
    public Applicant hasApplied(@PathVariable("job_id") long job_id, @PathVariable("user_id") long user_id) {
        List<Applicant> app = applicantsRepository.findByUserId(user_id);

        if (app == null) {
            System.out.println("null");
            return null;
        } else {
            for (Applicant a : app) {
                if (a.getJob().getId() == job_id) {
                    return a;
                }
            }
            return null;
        }

    }

    @GetMapping("/applied/{user_id}")
    public List<Job> appliedJobs(@PathVariable("user_id") long user_id) {
        List<Job> job = new ArrayList<>();
        List<Applicant> app = applicantsRepository.findByUserId(user_id);
        for (Applicant a : app) {
            job.add(a.getJob());
        }
        return job;
    }

    // get applicant jobs
    @GetMapping("/view_applicants/{user_id}")
    public List<Applicant> applicants(@PathVariable("user_id") Long id) {
        List<Applicant> result = new ArrayList<>();
        List<Job> jobs = jobsRepository.findByUserId(id);

        String m = "stringy";


        for (Job var : jobs) {
            result.addAll(applicantsRepository.findByJobId(var.getId()));
        }
        List<Applicant> app = applicantsRepository.findByJobId(jobs.get(0).getId());

        try {
            m = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(m);
       
        return result;
    }

  
}