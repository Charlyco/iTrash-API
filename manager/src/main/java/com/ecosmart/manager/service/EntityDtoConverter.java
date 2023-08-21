package com.ecosmart.manager.service;

import com.ecosmart.manager.data.Agent;
import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.Customer;
import com.ecosmart.manager.data.DisposalRequest;
import com.ecosmart.manager.dto.AgentDto;
import com.ecosmart.manager.dto.BinDto;
import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.dto.DisposalRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface EntityDtoConverter {
    Customer convertDtoToCustomer(CustomerDto customerDto);
    CustomerDto convertCustomerToDto(Customer customer);
    AgentDto convertAgentToDto(Agent agent);
    Agent convertDtoToAgent(AgentDto agentDto);
    DisposalRequestDto convertRequestToDto(DisposalRequest request);
    DisposalRequest convertDtoToRequest(DisposalRequestDto requestDto);
    Bin convertDtoToBin(BinDto binDto);
    BinDto convertBinToDto(Bin bin);
}
