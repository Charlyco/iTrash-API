package com.ecosmart.manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinRequestDto {
    private Integer requestId;
    private String binSize;
    private Integer customerId;
    private Double latitude;
    private Double longitude;
    private String address;
    private String ownership;
    private String requestStatus;
    private String requestDate;
}
