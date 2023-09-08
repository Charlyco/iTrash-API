package com.ecosmart.manager.dto;

import com.ecosmart.manager.data.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DisposalRequestDto {
    private Integer requestId;
    private String requestStatus;
    private Integer binId;
    private Integer customerId;
    private LocalDateTime requestDate;
    private Integer agentId;
}
