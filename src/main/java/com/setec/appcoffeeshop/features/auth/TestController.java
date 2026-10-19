package com.setec.appcoffeeshop.features.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// Access rules for these paths are in SecurityConfig
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/admin")
    public ResponseEntity<?> adminOnly(Authentication auth) {
        return ResponseEntity.ok(Map.of("message", "Hello admin " + auth.getName()));
    }

    @GetMapping("/user")
    public ResponseEntity<?> anyLoggedInUser(Authentication auth) {
        return ResponseEntity.ok(Map.of("message", "Hello " + auth.getName()));
    }
}
