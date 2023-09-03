package com.ecosmart.manager.controller;

import com.ecosmart.manager.data.AuthResponse;
import com.ecosmart.manager.dto.AdminDto;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public interface AuthController {
    @PostMapping("/customer")
    ResponseEntity<AuthResponse> createNewCustomer(@RequestBody CustomerDto customerDto);

    @PostMapping("/agent")
    ResponseEntity<AuthResponse> createNewAgent(@RequestBody AgentDto agentDto);

    @PostMapping("/admin")
    ResponseEntity<AuthResponse> createNewAdmin(@RequestBody AdminDto adminDto);

    @GetMapping("/signIn")
    ResponseEntity<AuthResponse> signIn(@RequestParam("userName") String userName, @RequestParam("password") String password);

}
