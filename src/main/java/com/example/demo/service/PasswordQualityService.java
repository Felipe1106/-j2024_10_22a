package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordQualityService {

    // Define the method to check if a password is valid
    public boolean isValidPassword(String password) {
        // Ensure the password is not null and meets the required length
        if (password == null || password.length() < 8) {
            return false;
        }

        // Check if the password contains at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check if the password contains at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if the password contains at least one special character
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return false;
        }

        // If all checks pass, return true
        return true;
    }
}
