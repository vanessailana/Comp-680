
package com.comp680.backend.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.comp680.backend.models.Support;
import com.comp680.backend.config.WebConfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.NestedServletException;

import junit.framework.Assert;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.ServletContext;
import javax.sound.sampled.AudioFormat.Encoding;

import org.json.simple.JSONObject;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SupportControllerTest  {
  
  
   /**
     * Note that the converter needs to be autowired into the test in order for
     * MockMvc to recognize it in the setup() method.
     */
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private Support support;

    @Test
    public void test_email_sends_successfully() throws Throwable
    {
        String uri = "/support";
       
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
        .post(uri)
        .param("email","marvinharootoonyan@gmail.com")
        .param("message", "Testing message")
        .content("{\"email\":\"marvinharootoonyan@gmail.com\", \"message\":\"Testing Message\"}")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).
        
        andDo(MockMvcResultHandlers.print()).andExpect(status().is2xxSuccessful()).andReturn();
       
      
       
    }
}
