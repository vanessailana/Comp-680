package com.comp680.backend.controllers;

import com.comp680.backend.repositories.ApplicantsRepository;
import com.comp680.backend.repositories.JobsRepository;
import com.comp680.backend.repositories.UsersRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Applicant;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders="*", maxAge = 3600)
public class ApplicantsController {

    @Autowired 
    ApplicantsRepository applicantsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    JobsRepository jobsRepository;
    
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody Applicant applicant ){
        JSONObject obj = new JSONObject();
        boolean exists_job = jobsRepository.existsById(applicant.getJob().getId());
        boolean exists_user = usersRepository.existsById(applicant.getUser().getId());
        if(exists_job && exists_user) {
            Applicant new_applicant = applicantsRepository.save(applicant);
            obj.put("applicant", new_applicant);
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        }
        obj.put("message", "Job or user does not exists");
        return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
    }
}