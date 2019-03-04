
package com.comp680.backend.controllers;

import com.comp680.backend.models.Support;

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

    /**
     * Test send email with empty body
     */
    @Test
    public void test_send_email_with_empty_body() throws Throwable
    {       
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email","marvinharootoonyan@gmail.com")
            .param("message", "")
            .content("{\"email\":\"marvinharootoonyan@gmail.com\", \"message\":\"\"}")
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
            .content("{\"email\":\"\", \"message\":\"support\"}")
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
            .param("email","marvinharootoonyan@gmail.com")
            .param("message", "Testing message")
            .content("{\"email\":\"marvinharootoonyan@gmail.com\", \"message\":\"Testing Message\"}")
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated()).andReturn();
    }
}
