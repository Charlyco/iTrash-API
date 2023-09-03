package com.ecosmart.manager.service;

import com.ecosmart.manager.data.*;
import com.ecosmart.manager.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface EntityDtoConverter {
    Customer convertDtoToCustomer(CustomerDto customerDto);
    CustomerDto convertCustomerToDto(Customer customer);
    AgentDto convertAgentToDto(Agent agent);
    Agent convertDtoToAgent(AgentDto agentDto);
    Admin convertDtoToAdmin(AdminDto adminDto);
    AdminDto convertAdminToDto(Admin admin);
    User convertDtoToUser(UserDto userDto);
    UserDto convertUserToDto(User user);
    DisposalRequestDto convertRequestToDto(DisposalRequest request);
    DisposalRequest convertDtoToRequest(DisposalRequestDto requestDto);
    Bin convertDtoToBin(BinDto binDto);
    BinDto convertBinToDto(Bin bin);

}
