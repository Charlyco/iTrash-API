package com.ecosmart.manager.controllerImpl;

import com.ecosmart.manager.controller.AuthController;
import com.ecosmart.manager.data.AuthResponse;
import com.ecosmart.manager.data.AuthResponseAdmin;
import com.ecosmart.manager.data.AuthResponseAgent;
import com.ecosmart.manager.data.AuthResponseCustomer;
import com.ecosmart.manager.dto.AdminDto;
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
    public ResponseEntity<AuthResponseCustomer> createNewCustomer(CustomerDto customerDto) {
        AuthResponseCustomer responseCustomer = authService.createNewCustomer(customerDto);
        if (responseCustomer.getCustomer().getUserId() != null) {
            return ResponseEntity.ok(responseCustomer);
        }
        else return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<AuthResponseAgent> createNewAgent(AgentDto agentDto) {
        AuthResponseAgent responseAgent = authService.createNewAgent(agentDto);
        if (responseAgent.getAgent().getUserId() != null) {
            return ResponseEntity.ok(responseAgent);
        }
       else return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<AuthResponseAdmin> createNewAdmin(AdminDto adminDto) {
        AuthResponseAdmin responseAdmin = authService.createNewAdmin(adminDto);
        if (responseAdmin.getAdmin().getUserId() != null) {
            return ResponseEntity.ok(responseAdmin);
        }
        else return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<AuthResponse> signIn(String userName, String password) {
        AuthResponse authResponse = authService.signIn(userName, password);
        if (authResponse.getUser() != null) {
            return ResponseEntity.ok(authResponse);
        }
        else return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> revokeToken(String token) {
        authService.revokeToken(token);
        return ResponseEntity.noContent().build();
    }
}
