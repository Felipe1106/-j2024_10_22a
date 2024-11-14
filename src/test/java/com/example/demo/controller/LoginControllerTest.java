package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGoodPassword_thenRedirectToQuiz() throws Exception {
        mockMvc.perform(post("/login").param("password", "GoodPassword123!"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/quiz"));
    }

    @Test
    void whenBadPassword_thenRedirectToBadPasswordPage() throws Exception {
        mockMvc.perform(post("/login").param("password", "weak"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/badpassword"));
    }
}
