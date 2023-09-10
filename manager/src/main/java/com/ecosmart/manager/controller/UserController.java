package com.ecosmart.manager.controller;

import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public interface UserController {
    @GetMapping("/customer/userName")
    ResponseEntity<CustomerDto> loadCustomerDetails(@RequestParam("userName") String userName);

    @GetMapping("/agent/userName")
    ResponseEntity<AgentDto> loadAgentDetails(@RequestParam("userName") String userName);

    @PutMapping("/customer/{id}/update")
    ResponseEntity<Void> updateCustomerDetails(@PathVariable("id") Integer customerId, @RequestBody CustomerDto customerUpdate);

    @PutMapping("/agent/{id}/update")
    ResponseEntity<Void> updateAgentDetails (@PathVariable("id") Integer agentId, @RequestBody AgentDto agentDto);

    @DeleteMapping("/customer/{id}/delete")
    ResponseEntity<Void> deleteCustomer (@PathVariable("id") Integer customerId);

    @DeleteMapping("/agent/{id}/delete")
    ResponseEntity<Void> deleteAgent (@PathVariable("id") Integer agentId);

    @GetMapping("/agents")
    ResponseEntity<List<AgentDto>> getAllAgents();
    @GetMapping("/customers")
    ResponseEntity<List<CustomerDto>> getAllCustomers();
    @GetMapping("/agent/{id}")
    ResponseEntity<AgentDto> getAgentDetailsById(@PathVariable("id") Integer agentId);
    @GetMapping("/customer/{id}")
    ResponseEntity<CustomerDto> getCustomerDetailsById(@PathVariable("id") Integer customerId);
}
