package com.ecosmart.manager.controller;

import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public interface UserController {
    @GetMapping("/customer/{userName}")
    ResponseEntity<CustomerDto> loadCustomerDetails(@PathVariable String userName);

    @GetMapping("/agent/{userName}")
    ResponseEntity<AgentDto> loadAgentDetails(@PathVariable String userName);

    @PutMapping("/customer/update/{id}")
    ResponseEntity<Void> updateCustomerDetails(@PathVariable Integer customerId, @RequestBody CustomerDto customerUpdate);

    @PutMapping("/agent/update/{id}")
    ResponseEntity<Void> updateAgentDetails (@PathVariable Integer agentId, @RequestBody AgentDto agentDto);

    @DeleteMapping("/customer/{id}")
    ResponseEntity<Void> deleteCustomer (@PathVariable Integer customerId);

    @DeleteMapping("/agent/{id}")
    ResponseEntity<Void> deleteAgent (@PathVariable Integer agentId);
}
