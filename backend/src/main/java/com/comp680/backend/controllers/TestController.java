package com.comp680.backend.controllers;
//https://mysterious-harbor-56923.herokuapp.com

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( maxAge = 3600 )
public class TestController {


    @GetMapping("/teest")
    public boolean test()
    {
       System.out.println("YeLLOW");
       return true;
    }
  
}