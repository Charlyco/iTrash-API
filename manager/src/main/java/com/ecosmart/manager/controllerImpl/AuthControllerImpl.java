package com.ecosmart.manager.controllerImpl;

import com.ecosmart.manager.controller.AuthController;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<String> createNewCustomer(CustomerDto customerDto) {
        return ResponseEntity.ok(authService.createNewCustomer(customerDto));
    }

    @Override
    public ResponseEntity<String> createNewAgent(AgentDto agentDto) {
        return ResponseEntity.ok(authService.createNewAgent(agentDto));
    }

    @Override
    public ResponseEntity<String> signIn(String userName, String password) {
        return ResponseEntity.ok(authService.signIn(userName, password));
    }
}
