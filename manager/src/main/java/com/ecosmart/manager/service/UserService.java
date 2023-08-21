package com.ecosmart.manager.service;

import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    CustomerDto loadCustomerDetails(String userName);
    void updateCustomerDetails(Integer customerId, CustomerDto customerUpdate);
    AgentDto loadAgentDetails(String userName);
    void updateAgentDetails(Integer agentId, AgentDto agentUpdate);
    void deleteCustomer(Integer customerId);
    void deleteAgent(Integer agentId);

    Boolean findCustomerById(Integer customerId);
    Boolean findAgentById(Integer agentId);
}
