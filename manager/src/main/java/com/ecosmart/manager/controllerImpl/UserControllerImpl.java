package com.ecosmart.manager.controllerImpl;

import com.ecosmart.manager.controller.UserController;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.service.UserService;
import org.springframework.http.ResponseEntity;

public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<CustomerDto> loadCustomerDetails(String userName) {
        return ResponseEntity.ok(userService.loadCustomerDetails(userName));
    }

    @Override
    public ResponseEntity<AgentDto> loadAgentDetails(String userName) {
        return ResponseEntity.ok(userService.loadAgentDetails(userName));
    }

    @Override
    public ResponseEntity<Void> updateCustomerDetails(Integer customerId, CustomerDto customerUpdate) {
        boolean updated = userService.updateCustomerDetails(customerId, customerUpdate);
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> updateAgentDetails(Integer agentId, AgentDto agentUpdate) {
        boolean updated = userService.updateAgentDetails(agentId, agentUpdate);
        if (updated) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(Integer customerId) {
        boolean deleted = userService.deleteCustomer(customerId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteAgent(Integer agentId) {
        boolean deleted = userService.deleteAgent(agentId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
