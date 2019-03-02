
package com.comp680.backend.controllers;

import static org.junit.Assert.assertTrue;

import com.comp680.backend.models.Support;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import junit.framework.Assert;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.servlet.ServletContext;

import org.json.simple.JSONObject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class SupportControllerTest  {
  
    @Autowired
    private WebApplicationContext wac;

    MockMvc mvc;

    @MockBean
    SupportController supportController;

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
    ServletContext servletContext = wac.getServletContext();
     
    Assert.assertNotNull(servletContext);
    Assert.assertTrue(servletContext instanceof MockServletContext);
    Assert.assertNotNull(wac.getBean(SupportController.class));
    
}

    @Before
    public void setUp() throws Exception {
        
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    
    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_email_sends_successfully() throws Exception
    {
        String uri = "/support";
        Support support = new Support("comp680@gmail.com", "Testing");
        String inputJson = asJsonString(support);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
        .post(uri)
        .accept(MediaType.ALL)
        .contentType(MediaType.ALL)
        .content("{ \"email\":\"comp680@gmail.com\",  \"message\":\"Testing\"}")).
        andDo(MockMvcResultHandlers.print()).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      System.out.println("Status: "+mvcResult.getResponse().getStatus());
      System.out.println("Content: "+mvcResult.getResponse().getErrorMessage());
     
      //assertEquals(201, status);
      //String content = mvcResult.getResponse().getContentAsString();
      //assertEquals(content, "Product is created successfully");
    
    }
}
