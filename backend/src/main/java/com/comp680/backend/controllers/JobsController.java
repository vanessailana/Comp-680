package com.comp680.backend.controllers;

import com.comp680.backend.repositories.JobsRepository;
import com.comp680.backend.repositories.QuestionsRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Job;
import com.comp680.backend.models.User;
import com.comp680.backend.models.Question;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

//https://mysterious-harbor-56923.herokuapp.com
@RestController
@CrossOrigin
public class JobsController {

    @Autowired
    JobsRepository jobRepo;

    @Autowired
    QuestionsRepository questRepo;

    @GetMapping("jobs/all")
    public List<Job> findAll() {
        List<Job> result = new ArrayList<Job>();
        jobRepo.findAll().forEach(result::add);
        return result;
    }


    @PostMapping("job/questions")
    public List<Question> submitQuestions(@RequestBody List<Question> questions)
    {
        questRepo.saveAll(questions);
        return questions;
    }


    @GetMapping("job/questions/{job_id}")
    public List<Question> getQuestions(@PathVariable long job_id)
    {
        List<Question> result = questRepo.findByJobId(job_id);
        return result;
    }

    @PostMapping("/createJob")
    public Job create(@RequestBody Job job) {
        return jobRepo.save(job);
    }

    @DeleteMapping("recruiter/my_jobs/{id}/{user_id}")
    public List<Job> delete(@PathVariable("id") Long id, @PathVariable("user_id") Long user_id) {
        jobRepo.deleteById(id);
        return null;
    }


    @PutMapping("recruiter/my_jobs/{user_id}")
    public Job editMyJob(@PathVariable("user_id") Long user_id, @RequestBody Job job) {
        // Job temp = jobRepo.getOne(job.id);
        return jobRepo.findByIdAndUserId(job.getId(), user_id);
    }


    @GetMapping("/recruiter/my_jobs/{user_id}")
    public List<Job> getMyJobs(@PathVariable("user_id") Long user_id) {
         return jobRepo.findByUserId(user_id);
     }

 }