package com.comp680.backend.controllers;

import com.comp680.backend.repositories.QuestionsRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;
import com.comp680.backend.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;


//https://mysterious-harbor-56923.herokuapp.com
@RestController
@CrossOrigin
public class QuestionsController {

@Autowired 
QuestionsRepository questRepo;

 @GetMapping("/questions")
    public List<Question> findAll()
    {
        return questRepo.findAll();
    }

 @PostMapping("/createQuestion")
    public Question create(@RequestBody Question quest)
    {
    	 return questRepo.save(quest);
    }

 @DeleteMapping("/questions/{id}")
     public List<Question> delete(@PathVariable("id") Long  question_id)
     {
         questRepo.deleteById(question_id);
         return questRepo.findAll();
     }

 }
