
package com.comp680.backend.controllers;

import com.comp680.backend.models.Support;

import org.apache.commons.lang.RandomStringUtils;
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

    private String generateRandomEmail(boolean domain) {
        String username = RandomStringUtils.randomAlphabetic(10);
        if(domain) {
            return username + "@gmail.com";
        }
        return username;
    }

    private String generateRandomMessage() {
        return RandomStringUtils.randomAlphanumeric(50);
    }

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
        String email = generateRandomEmail(true);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email", email)
            .content(createEmailJson(email, null).toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email without an email
     */
    @Test
    public void test_send_email_without_an_email() throws Throwable
    {
        String message = generateRandomMessage();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("message", message)
            .content(createEmailJson(null, message).toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email with empty message body
     */
    @Test
    public void test_send_email_with_empty_message_body() throws Throwable
    {
        String email = generateRandomEmail(true);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email", email)
            .param("message", "")
            .content(createEmailJson(email, "").toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email with empty email
     */
    @Test
    public void test_send_email_with_empty_email() throws Throwable
    {
        String message = generateRandomMessage();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email", "")
            .param("message", message)
            .content(createEmailJson("", message).toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test send email with invalid email
     */
    @Test
    public void test_send_email_with_invalid_email() throws Throwable
    {
        String email = generateRandomEmail(false);
        String message = generateRandomMessage();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email", email)
            .param("message", message)
            .content(createEmailJson(email, message).toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isUnprocessableEntity()).andReturn();
    }

    /**
     * Test send email successfully
     */
    @Test
    public void test_send_email_successfully() throws Throwable
    {
        String email = generateRandomEmail(true);
        String message = generateRandomMessage();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .param("email", email)
            .param("message", message)
            .content(createEmailJson(email, message).toString())
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated()).andReturn();
    }
}
