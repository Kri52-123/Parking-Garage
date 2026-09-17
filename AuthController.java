package com.parking.builder.controller;

import com.parking.builder.model.User;
import com.parking.builder.service.AuthService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service){this.service=service;}
    @PostMapping("/register") public Map<String,Object> register(@RequestBody AuthRequest r){ User u=service.register(r.username(),r.password()); return Map.of("message","Registration successful","userId",u.getId(),"username",u.getUsername()); }
    @PostMapping("/login") public Map<String,Object> login(@RequestBody AuthRequest r){ User u=service.login(r.username(),r.password()); return Map.of("message","Login successful","userId",u.getId(),"username",u.getUsername()); }
    public record AuthRequest(@NotBlank String username,@NotBlank String password){}
}
