package org.grh.website.controller;

import org.grh.website.model.UserRegistrationRequest;
import org.grh.website.service.CustomUserDetailsService;
import org.grh.website.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${baseUrl}/registration")
public class UserController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        userRegistrationService.registerUser(registrationRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}