package com.ecosmart.manager.dto;

import com.ecosmart.manager.data.Bin;
import com.ecosmart.manager.data.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DisposalRequestDto {
    private Integer requestId;
    private RequestStatus requestStatus;
    private Integer binId;
    private Integer customerId;
    private LocalDateTime requestDate;
    private Integer agentId;
}
