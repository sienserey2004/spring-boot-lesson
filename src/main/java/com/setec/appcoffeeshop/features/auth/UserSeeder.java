package com.setec.appcoffeeshop.features.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Creates the two test accounts on startup if they don't exist yet
@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        createIfMissing("admin", "admin123", Role.ADMIN);
        createIfMissing("user", "user123", Role.USER);
    }

    private void createIfMissing(String username, String password, Role role) {
        if (userRepository.existsByUsername(username)) return;

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
}
