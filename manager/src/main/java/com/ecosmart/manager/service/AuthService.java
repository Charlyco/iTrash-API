package com.ecosmart.manager.service;

import com.ecosmart.manager.data.AuthResponse;
import com.ecosmart.manager.dto.AdminDto;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse createNewCustomer(CustomerDto customerDto);
    AuthResponse createNewAgent(AgentDto agentDto);
    AuthResponse createNewAdmin(AdminDto adminDto);
    AuthResponse signIn(String userName,String password);

}
