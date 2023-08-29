package com.ecosmart.manager.controller;

import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public interface AuthController {
    @PostMapping("/customer")
    ResponseEntity<String> createNewCustomer(@RequestBody CustomerDto customerDto);

    @PostMapping("/agent")
    ResponseEntity<String> createNewAgent(@RequestBody AgentDto agentDto);

    @GetMapping("/signIn")
    ResponseEntity<String> signIn(@RequestParam("userName") String userName, @RequestParam("password") String password);

}
