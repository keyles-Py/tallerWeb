package com.app.citas.controller;

import com.app.citas.dto.UserDTO;
import com.app.citas.filter.security.JwtAuthFilter;
import com.app.citas.security.jwt.JwtUtil;
import com.app.citas.security.service.UserInfoDetail;
import com.app.citas.security.service.UserInfoService;
import com.app.citas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final UserInfoService userDetailsSvc;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO dto) {
        try {
            userService.register(dto);
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String user = body.get("email");
        String pass = body.get("password");

        if (user == null || pass == null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "email/password missing"));
        }

        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));
            UserDetails ud = userDetailsSvc.loadUserByUsername(user);

            List<String> roles = ud.getAuthorities()
                    .stream()
                    .map(auth -> auth.getAuthority())
                    .toList();

            String token = jwtUtil.generateToken(ud.getUsername(), roles);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("error", "Invalid credentials"));
        }
    }
}
