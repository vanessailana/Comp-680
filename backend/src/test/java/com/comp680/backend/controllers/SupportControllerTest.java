package com.comp680.backend.controllers;

import static org.junit.Assert.assertTrue;

import com.comp680.backend.models.Support;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class SupportControllerTest {
    SupportController supportController;

    @Before
    public void setUp() {
        supportController = new SupportController();
    }

    @Test
    public void test_email_sends_successfully() {
        Support support = new Support("comp680@gmail.com", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        ResponseEntity<?> response = supportController.sendEmail(support);
        System.out.println(response);
        // assertTrue(true);
    }
}