package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.Agent;
import com.ecosmart.manager.data.Customer;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.repository.AgentRepository;
import com.ecosmart.manager.repository.CustomerRepository;
import com.ecosmart.manager.service.EntityDtoConverter;
import com.ecosmart.manager.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;
    private final EntityDtoConverter entityDtoConverter;

    public UserServiceImpl(CustomerRepository customerRepository, AgentRepository agentRepository, EntityDtoConverter entityDtoConverter) {
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public CustomerDto loadCustomerDetails(String userName) {
        return entityDtoConverter.convertCustomerToDto(customerRepository.findCustomerByUserName(userName).orElseThrow());
    }

    @Override
    public void updateCustomerDetails(Integer customerId, CustomerDto customerUpdate) {
            Customer customer = entityDtoConverter.convertDtoToCustomer(customerUpdate);
            customer.setUserId(customerId);
            customerRepository.save(customer);
    }

    @Override
    public AgentDto loadAgentDetails(String userName) {
        return entityDtoConverter.convertAgentToDto(agentRepository.findAgentByUserName(userName).orElseThrow());
    }

    @Override
    public void updateAgentDetails(Integer agentId, AgentDto agentUpdate) {
            Agent agent = entityDtoConverter.convertDtoToAgent(agentUpdate);
            agent.setUserId(agentId);
            agentRepository.save(agent);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void deleteAgent(Integer agentId) {
            agentRepository.deleteById(agentId);
    }

    @Override
    public Boolean findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    @Override
    public Boolean findAgentById(Integer agentId) {
        return agentRepository.findById(agentId).isPresent();
    }
}
