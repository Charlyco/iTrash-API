package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.Token;
import com.ecosmart.manager.data.User;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.repository.AgentRepository;
import com.ecosmart.manager.repository.CustomerRepository;
import com.ecosmart.manager.repository.TokenRepository;
import com.ecosmart.manager.repository.UserRepository;
import com.ecosmart.manager.security.JwtService;
import com.ecosmart.manager.service.AuthService;
import com.ecosmart.manager.service.EntityDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final EntityDtoConverter entityDtoConverter;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(CustomerRepository customerRepository, AgentRepository agentRepository, UserRepository userRepository, JwtService jwtService, TokenRepository tokenRepository, EntityDtoConverter entityDtoConverter, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.entityDtoConverter = entityDtoConverter;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String createNewCustomer(CustomerDto customerDto) {
        List<CustomerDto> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customer -> customers.add(entityDtoConverter.convertCustomerToDto(customer)));
        Stream<CustomerDto> customerDtoStream = customers.stream();
        if(customerDtoStream.anyMatch(s -> Objects.equals(s.getUserName(), customerDto.getUserName()))) {
            return "Username already taken";
        }
        User createdUser = customerRepository.save(entityDtoConverter.convertDtoToCustomer(customerDto));
        var jwtToken = jwtService.generateToken(createdUser);
        return saveTokenAndGetAuthResponse(createdUser, jwtToken);
    }

    @Override
    public String createNewAgent(AgentDto agentDto) {
        List<AgentDto> agents = new ArrayList<>();
        agentRepository.findAll().forEach(agent -> agents.add(entityDtoConverter.convertAgentToDto(agent)));
        Stream<AgentDto> customerDtoStream = agents.stream();
        if(customerDtoStream.anyMatch(s -> Objects.equals(s.getUserName(), agentDto.getUserName()))) {
            return "Username already taken";
        }
        User createdUser = agentRepository.save(entityDtoConverter.convertDtoToAgent(agentDto));
        var jwtToken = jwtService.generateToken(createdUser);
        return saveTokenAndGetAuthResponse(createdUser, jwtToken);
    }

    @Override
    public String signIn(String userName, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );
        User authenticatedUser = userRepository.findUserByUserName(userName)
                .orElseThrow(); //Exception handling to be improved to include checks for username and password correctness
        var token = jwtService.generateToken(authenticatedUser);
        return saveTokenAndGetAuthResponse(authenticatedUser, token);

    }

    private String saveTokenAndGetAuthResponse(User user, String jwtToken) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setUserId(user.getUserId());
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
        return jwtToken;
    }

}