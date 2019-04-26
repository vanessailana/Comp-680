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
    public ResponseEntity<?> apply(@RequestBody Applicant applicant) {
        JSONObject obj = new JSONObject();
        boolean exists_job = jobsRepository.existsById(applicant.getJob().getId());
        boolean exists_user = usersRepository.existsById(applicant.getUser().getId());
        if (exists_job && exists_user) {
            Applicant new_applicant = applicantsRepository.save(applicant);
            obj.put("applicant", new_applicant);
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        }
        obj.put("message", "Job or user does not exists");
        return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/answers")
    public List<Answer> answers(@RequestBody List<Answer> answers) {
        List<Answer> res = new ArrayList<>();
        for (Answer var : answers) {
            res.add(this.answerRepository.save(var));
        }
        return res;
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