package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PasswordQualityService {

    
    public boolean isValidPassword(String password) {
        
        if (password == null || password.length() < 8) {
            return false;
        }

        
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return false;
        }

        
        return true;
    }
}
