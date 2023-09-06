package com.ecosmart.manager.dto;

import com.ecosmart.manager.data.BinOwnership;
import com.ecosmart.manager.data.BinStatus;
import com.ecosmart.manager.data.Location;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinDto {
    private Integer binId;
    private String binSize;
    private Double latitude;
    private Double longitude;
    private String address;
    private String ownership;
    private String binStatus;
    private Integer userId;
}
