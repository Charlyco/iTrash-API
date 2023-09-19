package com.ecosmart.manager.controller;

import com.ecosmart.manager.data.AuthResponse;
import com.ecosmart.manager.data.AuthResponseAdmin;
import com.ecosmart.manager.data.AuthResponseAgent;
import com.ecosmart.manager.data.AuthResponseCustomer;
import com.ecosmart.manager.dto.AdminDto;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public interface AuthController {
    @PostMapping("/customer")
    ResponseEntity<AuthResponseCustomer> createNewCustomer(@RequestBody CustomerDto customerDto);

    @PostMapping("/agent")
    ResponseEntity<AuthResponseAgent> createNewAgent(@RequestBody AgentDto agentDto);

    @PostMapping("/admin")
    ResponseEntity<AuthResponseAdmin> createNewAdmin(@RequestBody AdminDto adminDto);

    @GetMapping("/signIn")
    ResponseEntity<AuthResponse> signIn(@RequestParam("userName") String userName, @RequestParam("password") String password);
    @PutMapping("token")
    ResponseEntity<Void> revokeToken(@RequestParam("token") String token);

    @PutMapping("password")
    ResponseEntity<Boolean> resetPassword(@RequestParam("userName") String userName, @RequestParam("phone") String Phone, @RequestParam("password") String password);
}
