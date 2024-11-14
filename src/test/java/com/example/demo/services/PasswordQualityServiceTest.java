package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordQualityServiceTest {

    @Autowired
    private PasswordQualityService passwordQualityService;

    @BeforeEach
    void setUp() {
        // Optional setup before each test if needed
    }

    @Test
    void testGoodPassword() {
        // Example of a strong password that should pass
        String strongPassword = "StrongPass123!";
        assertTrue(passwordQualityService.isValidPassword(strongPassword), "Expected a strong password to be marked as valid");
    }

    @Test
    void testWeakPasswordShortLength() {
        // Example of a short password that should fail
        String shortPassword = "abc";
        assertFalse(passwordQualityService.isValidPassword(shortPassword), "Expected a short password to be marked as invalid");
    }

    @Test
    void testWeakPasswordNoNumbers() {
        // Example of a password with no numbers that should fail
        String noNumberPassword = "NoNumbersHere!";
        assertFalse(passwordQualityService.isValidPassword(noNumberPassword), "Expected a password without numbers to be marked as invalid");
    }

    @Test
    void testWeakPasswordNoSpecialCharacters() {
        // Example of a password without special characters that should fail
        String noSpecialCharPassword = "Password123";
        assertFalse(passwordQualityService.isValidPassword(noSpecialCharPassword), "Expected a password without special characters to be marked as invalid");
    }

    @Test
    void testWeakPasswordNoUppercaseLetters() {
        // Example of a password without uppercase letters that should fail
        String noUppercasePassword = "weakpassword123!";
        assertFalse(passwordQualityService.isValidPassword(noUppercasePassword), "Expected a password without uppercase letters to be marked as invalid");
    }
}
