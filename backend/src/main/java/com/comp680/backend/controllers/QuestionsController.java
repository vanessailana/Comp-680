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
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestBody;


//https://mysterious-harbor-56923.herokuapp.com
@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600 )
@RestController
public class QuestionsController {

@Autowired 
QuestionsRepository questRepo;

@GetMapping("/questions")
public Iterable<Question> findAll()
{
    return questRepo.findAll();
}

@PostMapping("/createQuestion")
public List<Question> create(@RequestBody List<Question> quest)
{
    List<Question> res = new ArrayList();
    for(Question q : quest)
    {
        res.add(questRepo.save(q));
    }
    return res;
}


@GetMapping("/questions/{id}")
public List<Question> questions(@PathVariable("id") Long job_id)
{
    return questRepo.findByJobId(job_id);
}

 @DeleteMapping("/questions/{id}")
     public Iterable<Question> delete(@PathVariable("id") Long  question_id)
     {
         questRepo.deleteById(question_id);
         return questRepo.findAll();
     }

 }
