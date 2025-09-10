package com.elu.profileservicesuperfs.controller;


import com.elu.profileservicesuperfs.UserResonseDto;
import com.elu.profileservicesuperfs.dto.UserDtoOpenFeign;
import com.elu.profileservicesuperfs.feign.AuthFeignClient;
import com.elu.profileservicesuperfs.util.JwtUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private JwtUtil jwtUtil;

    @Autowired
    private AuthFeignClient authFeignClient;

    public ProfileController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/test")
    public Map<String, Object> test(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.replace("Bearer ", "");
        Claims claims = jwtUtil.extractAllClaims(token);

        Map<String, Object> response = new HashMap<>();
        response.put("id", claims.get("id"));
        response.put("username", claims.get("username"));
        response.put("email", claims.get("email"));
        response.put("role", claims.get("role"));
        return response;
    }

    @GetMapping("/get-users")
    @CircuitBreaker(name = "authServiceBreaker", fallbackMethod = "fallbackGetUsers")
    public List<UserDtoOpenFeign> getUsers() {
        System.out.println("inside getUsers");
        return authFeignClient.getUsers();
    }

    @PostMapping("/update-user")
    @CircuitBreaker(name = "authServiceBreaker", fallbackMethod = "fallbackGetUsers")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UserDtoOpenFeign request) {
        System.out.println("inside updateUser");
        ResponseEntity<Map<String, Object>> response
                = authFeignClient.updateUser(request);
        return response;
    }

    // Fallback method (must match method signature)
    public List<UserResonseDto> fallbackGetUsers(Throwable throwable) {
        System.out.println("Auth service is down: " + throwable.getMessage());
        return List.of(new UserResonseDto("N/A", "Fallback User")); // dummy response
    }

}
