package org.grh.website.controller;

import org.grh.website.model.UserRegistrationRequest;
import org.grh.website.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${baseUrl}/registration")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        userRegistrationService.registerUser(registrationRequest);
        return ResponseEntity.ok("Registration successful. Please check your email for confirmation.");
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmUser(@RequestParam("token") String token) {
        boolean isConfirmed = userRegistrationService.confirmUser(token);
        if (isConfirmed) {
            return ResponseEntity.ok("User confirmed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid confirmation token.");
        }
    }
}