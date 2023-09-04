package com.ecosmart.manager.service;

import com.ecosmart.manager.data.AuthResponse;
import com.ecosmart.manager.data.AuthResponseAdmin;
import com.ecosmart.manager.data.AuthResponseAgent;
import com.ecosmart.manager.data.AuthResponseCustomer;
import com.ecosmart.manager.dto.AdminDto;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponseCustomer createNewCustomer(CustomerDto customerDto);
    AuthResponseAgent createNewAgent(AgentDto agentDto);
    AuthResponseAdmin createNewAdmin(AdminDto adminDto);
    AuthResponse signIn(String userName, String password);

}
