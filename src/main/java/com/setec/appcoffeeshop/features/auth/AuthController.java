package com.setec.appcoffeeshop.features.auth;

import com.setec.appcoffeeshop.features.auth.dto.LoginRequest;
import com.setec.appcoffeeshop.features.auth.dto.LoginResponse;
import com.setec.appcoffeeshop.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        var user = userRepository.findByUsername(request.username()).orElse(null);

        if (user == null || !passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
    }
}
