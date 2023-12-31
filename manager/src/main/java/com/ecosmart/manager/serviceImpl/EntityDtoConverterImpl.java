package com.ecosmart.manager.serviceImpl;

import com.ecosmart.manager.data.*;
import com.ecosmart.manager.dto.*;
import com.ecosmart.manager.repository.*;
import com.ecosmart.manager.service.EntityDtoConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        customer.setFullName(customerDto.getFullName());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setRole(customerDto.getRole());
        customer.setPhoneNumber(customerDto.getPhoneNumber());


        return customer;
    }

    @Override
    public CustomerDto convertCustomerToDto(Customer customer) {
        List<Integer> binIds = new ArrayList<>();
        List<Integer> requestIds = new ArrayList<>();
        if (customer.getBinList() != null) {
            customer.getBinList().forEach(bin -> binIds.add(bin.getBinId()));
        }
        if (customer.getRequests() != null) {
            customer.getRequests().forEach(disposalRequest -> requestIds.add(disposalRequest.getRequestId()));
        }

        CustomerDto customerDto = new CustomerDto();
        customerDto.setUserId(customer.getUserId());
        customerDto.setUserName(customer.getUsername());
        customerDto.setFullName(customer.getFullName());
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
        if (agent.getRequestListHandled() != null) {
            agent.getRequestListHandled().forEach(disposalRequest -> requestIds.add(disposalRequest.getRequestId()));
        }
        AgentDto agentDto = new AgentDto();
        agentDto.setUserId(agent.getUserId());
        agentDto.setUserName(agent.getUsername());
        agentDto.setFullName(agent.getFullName());
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
        //List<DisposalRequest> disposalRequests = requestRepository.findAllById(agentDto.getRequestIds());
        Agent agent = new Agent();
        agent.setUserId(agentDto.getUserId());
        agent.setUserName(agentDto.getUserName());
        agent.setFullName(agentDto.getFullName());
        agent.setPassword(passwordEncoder.encode(agentDto.getPassword()));
        agent.setAddress(agentDto.getAddress());
        agent.setEmail(agentDto.getEmail());
        agent.setRole(agentDto.getRole());
        agent.setPhoneNumber(agentDto.getPhoneNumber());
        agent.setCurrentLocation(agentDto.getCurrentLocation());
        //agent.setRequestListHandled(disposalRequests);
        return agent;
    }

    @Override
    public Admin convertDtoToAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setUserId(adminDto.getUserId());
        admin.setUserName(adminDto.getUserName());
        admin.setFullName(adminDto.getFullName());
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
        adminDto.setFullName(admin.getFullName());
        adminDto.setPassword(admin.getPassword());
        adminDto.setAddress(admin.getAddress());
        adminDto.setEmail(admin.getEmail());
        adminDto.setRole(admin.getRole());
        adminDto.setPhoneNumber(admin.getPhoneNumber());
        return adminDto;
    }

    @Override
    public User convertDtoToUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setFullName(userDto.getFullName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }

    @Override
    public UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    @Override
    public DisposalRequestDto convertRequestToDto(DisposalRequest request) {
        DisposalRequestDto requestDto = new DisposalRequestDto();
        requestDto.setRequestId(request.getRequestId());
        requestDto.setRequestStatus(request.getRequestStatus().name());
        requestDto.setBinId(request.getBinId());
        requestDto.setCustomerId(request.getCustomer().getUserId());
        requestDto.setRequestDate(String.valueOf(request.getRequestDate()));
        if (request.getAgent() != null) {
            requestDto.setAgentId(request.getAgent().getUserId());
        }

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
        request.setRequestStatus(RequestStatus.valueOf(requestDto.getRequestStatus()));
        request.setBinId(requestDto.getBinId());
        request.setCustomer(customerRepository.findById(requestDto.getCustomerId()).orElseThrow());
        request.setRequestDate(LocalDateTime.parse(requestDto.getRequestDate()));
        if (requestDto.getAgentId() != null) {
            request.setAgent(agentRepository.findById(requestDto.getAgentId()).orElseThrow());
        }


        return request;
    }

    @Override
    public Bin convertDtoToBin(BinDto binDto) {
        Integer binId = null;
        if (binDto.getBinId() != null) {
            binId = binDto.getBinId();
        }
        Location location = new Location();
        location.setLatitude(binDto.getLatitude());
        location.setLongitude(binDto.getLongitude());

        Bin bin = new Bin();
        bin.setBinId(binId);
        bin.setBinSize(BinSize.valueOf(binDto.getBinSize()));
        bin.setLocation(location);
        bin.setDetailedAddress(binDto.getAddress());
        bin.setBinStatus(BinStatus.valueOf(binDto.getBinStatus()));
        bin.setOwnership(BinOwnership.valueOf(binDto.getOwnership()));
        bin.setCustomer(customerRepository.findById(binDto.getUserId()).orElseThrow());
        return bin;
    }

    @Override
    public BinDto convertBinToDto(Bin bin) {
        Double latitude = bin.getLocation().getLatitude();
        Double longitude = bin.getLocation().getLongitude();

        BinDto binDto = new BinDto();
        binDto.setBinId(bin.getBinId());
        binDto.setBinSize(bin.getBinSize().name());
        binDto.setLatitude(latitude);
        binDto.setLongitude(longitude);
        binDto.setAddress(bin.getDetailedAddress());
        binDto.setBinStatus(bin.getBinStatus().name());
        binDto.setOwnership(bin.getOwnership().name());
        binDto.setUserId(bin.getCustomer().getUserId());
        return binDto;
    }

    @Override
    public BinRequest convertDtoToBinRequest(BinRequestDto binRequestDto) {
        Integer requestId = null;
        if (binRequestDto.getRequestId() != null) {
            requestId = binRequestDto.getRequestId();
        }
        Location location = new Location();
        location.setLatitude(binRequestDto.getLatitude());
        location.setLongitude(binRequestDto.getLongitude());

        BinRequest binRequest = new BinRequest();
        binRequest.setRequestId(requestId);
        binRequest.setBinSize(BinSize.valueOf(binRequestDto.getBinSize()));
        binRequest.setCustomer(customerRepository.findById(binRequestDto.getCustomerId()).orElseThrow());
        binRequest.setLocation(location);
        binRequest.setDetailedAddress(binRequestDto.getAddress());
        binRequest.setBinOwnership(BinOwnership.valueOf(binRequestDto.getOwnership()));
        binRequest.setRequestStatus(BinRequestStatus.valueOf(binRequestDto.getRequestStatus()));
        binRequest.setRequestDate(LocalDateTime.parse(binRequestDto.getRequestDate()));
        return binRequest;
    }

    @Override
    public BinRequestDto convertBinRequestToDto(BinRequest binRequest) {
        BinRequestDto requestDto = new BinRequestDto();
        requestDto.setRequestId(binRequest.getRequestId());
        requestDto.setBinSize(binRequest.getBinSize().name());
        requestDto.setCustomerId(binRequest.getCustomer().getUserId());
        requestDto.setLatitude(binRequest.getLocation().getLatitude());
        requestDto.setLongitude(binRequest.getLocation().getLongitude());
        requestDto.setAddress(binRequest.getDetailedAddress());
        requestDto.setOwnership(binRequest.getBinOwnership().name());
        requestDto.setRequestStatus(binRequest.getRequestStatus().name());
        requestDto.setRequestDate(binRequest.getRequestDate().toString());
        return requestDto;
    }
}
