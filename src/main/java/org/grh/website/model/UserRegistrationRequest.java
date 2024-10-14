package org.grh.website.model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String email;

    // Getters and setters
}