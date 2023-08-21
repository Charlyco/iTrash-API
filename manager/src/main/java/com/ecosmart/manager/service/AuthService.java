package com.ecosmart.manager.service;

import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String createNewCustomer(CustomerDto customerDto);

    String createNewAgent(AgentDto agentDto);

    String signIn(String userName,String password);

}
