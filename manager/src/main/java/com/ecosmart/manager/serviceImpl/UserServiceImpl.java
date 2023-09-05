package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.Agent;
import com.ecosmart.manager.data.Customer;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.repository.AgentRepository;
import com.ecosmart.manager.repository.CustomerRepository;
import com.ecosmart.manager.repository.UserRepository;
import com.ecosmart.manager.service.EntityDtoConverter;
import com.ecosmart.manager.service.UserService;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;
    private final EntityDtoConverter entityDtoConverter;

    public UserServiceImpl(
            CustomerRepository customerRepository,
            AgentRepository agentRepository,
            EntityDtoConverter entityDtoConverter
    ) {
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public CustomerDto loadCustomerDetails(String userName) {
        Customer customer = customerRepository.findCustomerByUserName(userName).orElseThrow();
        return entityDtoConverter.convertCustomerToDto(customer);
    }

    @Override
    public Boolean updateCustomerDetails(Integer customerId, CustomerDto customerUpdate) {
        if (customerRepository.findById(customerId).isPresent()) {
            Customer customer = entityDtoConverter.convertDtoToCustomer(customerUpdate);
            customer.setUserId(customerId);
            customerRepository.save(customer);
            return true;
        }else return false;
    }

    @Override
    public AgentDto loadAgentDetails(String userName) {
        return entityDtoConverter.convertAgentToDto(agentRepository.findAgentByUserName(userName).orElseThrow());
    }

    @Override
    public Boolean updateAgentDetails(Integer agentId, AgentDto agentUpdate) {
        if (agentRepository.findById(agentId).isPresent()) {
            Agent agent = entityDtoConverter.convertDtoToAgent(agentUpdate);
            agent.setUserId(agentId);
            agentRepository.save(agent);
            return true;
        }else return false;
    }

    @Override
    public Boolean deleteCustomer(Integer customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            customerRepository.deleteById(customerId);
            return true;
        }else return false;
    }

    @Override
    public Boolean deleteAgent(Integer agentId) {
        if (agentRepository.findById(agentId).isPresent()) {
            agentRepository.deleteById(agentId);
            return true;
        }else return false;
    }

    @Override
    public List<AgentDto> getAllAgents() {
        List<Agent> agents = agentRepository.findAll();
        List<AgentDto> agentDtos = new ArrayList<>();
        if (agents != null) {
            agents.forEach(agent -> agentDtos.add(entityDtoConverter.convertAgentToDto(agent)));
        }
        return agentDtos;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        if (customerList != null) {
            customerList.forEach(customer -> customerDtoList.add(entityDtoConverter.convertCustomerToDto(customer)));
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto findCustomerById(Integer customerId) {
        return entityDtoConverter.convertCustomerToDto(customerRepository.findById(customerId).orElseThrow());
    }

    @Override
    public AgentDto findAgentById(Integer agentId) {
        return entityDtoConverter.convertAgentToDto(agentRepository.findById(agentId).orElseThrow());
    }
}
