package com.ecosmart.manager.controller;

import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public interface UserController {
    @GetMapping("/get/customer/{userName}")
    ResponseEntity<CustomerDto> loadCustomerDetails(@PathVariable("userName") String userName);

    @GetMapping("/get/agent/{userName}")
    ResponseEntity<AgentDto> loadAgentDetails(@PathVariable("userName") String userName);

    @PutMapping("/update/customer/{id}")
    ResponseEntity<Void> updateCustomerDetails(@PathVariable("id") Integer customerId, @RequestBody CustomerDto customerUpdate);

    @PutMapping("/update/agent/{id}")
    ResponseEntity<Void> updateAgentDetails (@PathVariable("id") Integer agentId, @RequestBody AgentDto agentDto);

    @DeleteMapping("/delete/customer/{id}")
    ResponseEntity<Void> deleteCustomer (@PathVariable("id") Integer customerId);

    @DeleteMapping("/delete/agent/{id}")
    ResponseEntity<Void> deleteAgent (@PathVariable("id") Integer agentId);
}
