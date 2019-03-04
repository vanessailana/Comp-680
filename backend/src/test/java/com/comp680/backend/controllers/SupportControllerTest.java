
package com.comp680.backend.controllers;

import com.comp680.backend.models.Support;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SupportControllerTest  {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Support support;

    private String URI = "/support";

    private JSONObject createEmailJson(String email, String message) {
        JSONObject json = new JSONObject();
        if(email != null) {
            json.put("email", email);
        }
        if(message != null) {
            json.put("message", message);
        }
        return json;
    }

    /**
     * Test send email without a message body
     */
    @Test
    public void test_send_email_without_a_message_body() throws Throwable
    {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email","comp680@gmail.com")
            .content(createEmailJson("comp680@gmail.com", null).toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email without an email
     */
    @Test
    public void test_send_email_without_an_email() throws Throwable
    {  
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("message", "support")
            .content(createEmailJson(null, "support").toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email with empty message body
     */
    @Test
    public void test_send_email_with_empty_message_body() throws Throwable
    {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email","comp680@gmail.com")
            .param("message", "")
            .content(createEmailJson("comp680@gmail.com", "").toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email with empty email
     */
    @Test
    public void test_send_email_with_empty_email() throws Throwable
    {  
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email","")
            .param("message", "support")
            .content(createEmailJson("", "support").toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email with invalid email
     */
    @Test
    public void test_send_email_with_invalid_email() throws Throwable
    {  
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email","comp680")
            .param("message", "support")
            .content(createEmailJson("comp680", "support").toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isUnprocessableEntity()).andReturn();
    }

    /**
     * Test send email successfully
     */
    @Test
    public void test_send_email_successfully() throws Throwable
    {  
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email","comp680@gmail.com")
            .param("message", "Testing message")
            .content(createEmailJson("comp680@gmail.com", "Testing message").toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated()).andReturn();
    }
}
