package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.*;
import com.ecosmart.manager.dto.AdminDto;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.repository.*;
import com.ecosmart.manager.security.JwtService;
import com.ecosmart.manager.service.AuthService;
import com.ecosmart.manager.service.EntityDtoConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final EntityDtoConverter entityDtoConverter;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(CustomerRepository customerRepository, AgentRepository agentRepository, UserRepository userRepository, AdminRepository adminRepository, JwtService jwtService, TokenRepository tokenRepository, EntityDtoConverter entityDtoConverter, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.entityDtoConverter = entityDtoConverter;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseCustomer createNewCustomer(CustomerDto customerDto) {
        List<CustomerDto> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customer -> customers.add(entityDtoConverter.convertCustomerToDto(customer)));
        Stream<CustomerDto> customerDtoStream = customers.stream();
        if (customerDtoStream.noneMatch(s -> Objects.equals(s.getUserName(), customerDto.getUserName()))
        ) { if (customers.stream().noneMatch(user -> Objects.equals(user.getEmail(), customerDto.getEmail()))) {
            if (customers.stream().noneMatch(user -> Objects.equals(user.getPhoneNumber(), customerDto.getPhoneNumber()))) {
                Customer createdCustomer = customerRepository.save(entityDtoConverter.convertDtoToCustomer(customerDto));
                var jwtToken = jwtService.generateToken(createdCustomer);
                saveToken(createdCustomer, jwtToken);
                AuthResponseCustomer authResponse = new AuthResponseCustomer();
                authResponse.setCustomer(entityDtoConverter.convertCustomerToDto(createdCustomer));
                authResponse.setToken(jwtToken);
                return authResponse;
                }
            }
        }
        return new AuthResponseCustomer();
    }

    @Override
    public AuthResponseAgent createNewAgent(AgentDto agentDto) {
        List<AgentDto> agents = new ArrayList<>();
        agentRepository.findAll().forEach(agent -> agents.add(entityDtoConverter.convertAgentToDto(agent)));
        Stream<AgentDto> agentDtoStream = agents.stream();
        if (agentDtoStream.noneMatch(s -> Objects.equals(s.getUserName(), agentDto.getUserName()))) {
            if (agents.stream().noneMatch(agent -> Objects.equals(agent.getEmail(), agentDto.getEmail()))) {
                if (agents.stream().noneMatch(agent -> Objects.equals(agent.getEmail(), agentDto.getEmail()))) {
                    Agent createdAgent = agentRepository.save(entityDtoConverter.convertDtoToAgent(agentDto));
                    var jwtToken = jwtService.generateToken(createdAgent);
                    saveToken(createdAgent, jwtToken);
                    AuthResponseAgent authResponse = new AuthResponseAgent();
                    authResponse.setAgent(entityDtoConverter.convertAgentToDto(createdAgent));
                    authResponse.setToken(jwtToken);
                    return authResponse;
                }
            }
        }
        return new AuthResponseAgent();
    }

    @Override
    public AuthResponseAdmin createNewAdmin(AdminDto adminDto) {
        List<AdminDto> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admin -> admins.add(entityDtoConverter.convertAdminToDto(admin)));
        Stream<AdminDto> adminDtoStream = admins.stream();
        if (adminDtoStream.noneMatch(adm -> adm.getUserName().equals(adminDto.getUserName()))) {
            if (admins.stream().noneMatch(admin -> Objects.equals(admin.getPhoneNumber(), adminDto.getPhoneNumber()))) {
                if (admins.stream().noneMatch(admin -> Objects.equals(admin.getEmail(), adminDto.getEmail()))) {
                    Admin createdAdmin = adminRepository.save(entityDtoConverter.convertDtoToAdmin(adminDto));
                    var jwtToken = jwtService.generateToken(createdAdmin);
                    saveToken(createdAdmin, jwtToken);
                    AuthResponseAdmin authResponse = new AuthResponseAdmin();
                    authResponse.setAdmin(entityDtoConverter.convertAdminToDto(createdAdmin));
                    authResponse.setToken(jwtToken);
                    return authResponse;
                }
            }
        }
        return new AuthResponseAdmin();
    }

    @Override
    public AuthResponse signIn(String userName, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        password
                )
        );
        User authenticatedUser = userRepository.findUserByUserName(userName)
                .orElseThrow(); //Exception handling to be improved to include checks for username and password correctness
        var token = jwtService.generateToken(authenticatedUser);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(entityDtoConverter.convertUserToDto(authenticatedUser));
        authResponse.setToken(token);
        return authResponse;
    }

    @Override
    public void revokeToken(String token) {
        Token tokenToRevoke = tokenRepository.findTokenByToken(token);
        if (tokenToRevoke != null) {
            tokenToRevoke.setRevoked(true);
            tokenRepository.save(tokenToRevoke);
        }
    }

    private void saveToken(User user, String jwtToken) {
        Token token = new Token();
        token.setToken(jwtToken);
        token.setUserId(user.getUserId());
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

}
