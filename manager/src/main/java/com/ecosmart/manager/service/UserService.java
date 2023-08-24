package com.ecosmart.manager.service;

import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    CustomerDto loadCustomerDetails(String userName);
    Boolean updateCustomerDetails(Integer customerId, CustomerDto customerUpdate);
    AgentDto loadAgentDetails(String userName);
    Boolean updateAgentDetails(Integer agentId, AgentDto agentUpdate);
    Boolean deleteCustomer(Integer customerId);
    Boolean deleteAgent(Integer agentId);

    CustomerDto findCustomerById(Integer customerId);
    AgentDto findAgentById(Integer agentId);
}
