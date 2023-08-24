package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.*;
import com.ecosmart.manager.dto.*;
import com.ecosmart.manager.repository.*;
import com.ecosmart.manager.service.EntityDtoConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntityDtoConverterImpl implements EntityDtoConverter {
    private final DisposalRequestRepository requestRepository;
    private final BinRepository binRepository;
    private final CustomerRepository customerRepository;
    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;

    public EntityDtoConverterImpl(DisposalRequestRepository requestRepository, BinRepository binRepository, CustomerRepository customerRepository, AgentRepository agentRepository, PasswordEncoder passwordEncoder) {
        this.requestRepository = requestRepository;
        this.binRepository = binRepository;
        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer convertDtoToCustomer(CustomerDto customerDto) {
        //List<Bin> binList = binRepository.findAllById(customerDto.getBinIds());
        //List<DisposalRequest> disposalRequests = requestRepository.findAllById(customerDto.getRequestIds());
        Integer userId = null;
        if (customerDto.getUserId() != null){
            userId = customerDto.getUserId();
        }
        Customer customer = new Customer();
        customer.setUserId(userId);
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setRole(customerDto.getRole());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        //customer.setBinList(binList);
        //customer.setRequests(disposalRequests);

        return customer;
    }

    @Override
    public CustomerDto convertCustomerToDto(Customer customer) {
        List<Integer> binIds = new ArrayList<>();
        List<Integer> requestIds = new ArrayList<>();
        customer.getBinList().forEach(bin -> binIds.add(bin.getBinId()));
        customer.getRequests().forEach(disposalRequest -> requestIds.add(disposalRequest.getRequestId()));

        CustomerDto customerDto = new CustomerDto();
        customerDto.setUserId(customer.getUserId());
        customerDto.setUserName(customer.getUsername());
        customerDto.setPassword(customer.getPassword());
        customerDto.setAddress(customer.getAddress());
        customerDto.setEmail(customer.getEmail());
        customerDto.setRole(customer.getRole());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setBinIds(binIds);
        customerDto.setRequestIds(requestIds);

        return customerDto;
    }

    @Override
    public AgentDto convertAgentToDto(Agent agent) {
        List<Integer> requestIds = new ArrayList<>();
        agent.getRequestListHandled().forEach(disposalRequest -> requestIds.add(disposalRequest.getRequestId()));
        AgentDto agentDto = new AgentDto();
        agentDto.setUserId(agent.getUserId());
        agentDto.setUserName(agent.getUsername());
        agentDto.setPassword(agent.getPassword());
        agentDto.setAddress(agent.getAddress());
        agentDto.setEmail(agent.getEmail());
        agentDto.setRole(agent.getRole());
        agentDto.setPhoneNumber(agent.getPhoneNumber());
        agentDto.setCurrentLocation(agent.getCurrentLocation());
        agentDto.setRequestIds(requestIds);
        return agentDto;
    }

    @Override
    public Agent convertDtoToAgent(AgentDto agentDto) {
        List<DisposalRequest> disposalRequests = requestRepository.findAllById(agentDto.getRequestIds());
        Agent agent = new Agent();
        agent.setUserId(agentDto.getUserId());
        agent.setUserName(agentDto.getUserName());
        agent.setPassword(passwordEncoder.encode(agentDto.getPassword()));
        agent.setAddress(agentDto.getAddress());
        agent.setEmail(agentDto.getEmail());
        agent.setRole(agentDto.getRole());
        agent.setPhoneNumber(agentDto.getPhoneNumber());
        agent.setCurrentLocation(agentDto.getCurrentLocation());
        agent.setRequestListHandled(disposalRequests);
        return agent;
    }

    @Override
    public Admin convertDtoToAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setUserId(adminDto.getUserId());
        admin.setUserName(adminDto.getUserName());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setAddress(adminDto.getAddress());
        admin.setEmail(adminDto.getEmail());
        admin.setRole(adminDto.getRole());
        admin.setPhoneNumber(adminDto.getPhoneNumber());
        return admin;
    }

    @Override
    public AdminDto convertAdminToDto(Admin admin) {
        AdminDto adminDto = new AdminDto();
        adminDto.setUserId(admin.getUserId());
        adminDto.setUserName(admin.getUsername());
        adminDto.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminDto.setAddress(admin.getAddress());
        adminDto.setEmail(admin.getEmail());
        adminDto.setRole(admin.getRole());
        adminDto.setPhoneNumber(admin.getPhoneNumber());
        return adminDto;
    }

    @Override
    public DisposalRequestDto convertRequestToDto(DisposalRequest request) {
        DisposalRequestDto requestDto = new DisposalRequestDto();
        requestDto.setRequestId(request.getRequestId());
        requestDto.setRequestStatus(request.getRequestStatus());
        requestDto.setBinId(request.getBin().getBinId());
        requestDto.setCustomerId(request.getCustomer().getUserId());
        requestDto.setAgentId(request.getAgent().getUserId());

        return requestDto;
    }

    @Override
    public DisposalRequest convertDtoToRequest(DisposalRequestDto requestDto) {
        Integer requestId = null;
        if (requestDto.getRequestId() != null) {
            requestId = requestDto.getRequestId();
        }
        DisposalRequest request = new DisposalRequest();
        request.setRequestId(requestId);
        request.setRequestStatus(requestDto.getRequestStatus());
        request.setBin(binRepository.findById(requestDto.getBinId()).orElseThrow());
        request.setCustomer(customerRepository.findById(requestDto.getCustomerId()).orElseThrow());
        request.setAgent(agentRepository.findById(requestDto.getAgentId()).orElseThrow());

        return request;
    }

    @Override
    public Bin convertDtoToBin(BinDto binDto) {
        Integer binId = null;
        if (binDto.getBinId() != null) {
            binId = binDto.getBinId();
        }
        Bin bin = new Bin();
        bin.setBinId(binId);
        bin.setBinSize(binDto.getBinSize());
        bin.setBinStatus(binDto.getBinStatus());
        bin.setOwnership(binDto.getOwnership());
        bin.setLocation(binDto.getLocation());
        bin.setCustomer(customerRepository.findById(binDto.getUserId()).orElseThrow());
        return bin;
    }

    @Override
    public BinDto convertBinToDto(Bin bin) {
        BinDto binDto = new BinDto();
        binDto.setBinId(bin.getBinId());
        binDto.setBinSize(bin.getBinSize());
        binDto.setBinStatus(bin.getBinStatus());
        binDto.setOwnership(bin.getOwnership());
        binDto.setLocation(bin.getLocation());
        binDto.setUserId(bin.getCustomer().getUserId());
        return binDto;
    }
}
