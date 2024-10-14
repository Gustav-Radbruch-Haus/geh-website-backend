package org.grh.website.service;

import org.grh.website.model.User;
import org.grh.website.model.UserRegistrationRequest;
import org.grh.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Autowired
    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    public void registerUser(UserRegistrationRequest registrationRequest) {
        User user = new User();

        // Disable the User and set Confirmation Token
        user.setConfirmationToken(UUID.randomUUID().toString());
        user.setActive(false);
        user.setLocked(false);

        // Fill the rest with the User
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());
        userRepository.save(user);

        // Send confirmation email
        sendConfirmationEmail(user);
    }

    private void sendConfirmationEmail(User user) {
        String confirmationUrl = "/registration/confirm?token=" + generateConfirmationToken(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Registration Confirmation");
        message.setText("Please confirm your registration by clicking the link below:\n" + confirmationUrl);
        mailSender.send(message);
    }

    private String generateConfirmationToken(User user) {
        // Generate a token, save it to the user (or a separate table), and return the token
        String token = UUID.randomUUID().toString();
        // Assuming there's a field or method to save token
        user.setConfirmationToken(token);
        userRepository.save(user);
        return token;
    }

    public boolean confirmUser(String token) {
        // Find user by token, if exists set them active, remove token and save user
        Optional<User> user = userRepository.findByConfirmationToken(token);
        if (user.isPresent()) {
            User u = user.get();
            u.setActive(true);
            u.setConfirmationToken(null);
            userRepository.save(u);
            return true;
        }
        return false;
    }
}