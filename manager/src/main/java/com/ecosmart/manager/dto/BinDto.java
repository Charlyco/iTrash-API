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
    private Double binSize;
    private Location location;
    private String address;
    private BinOwnership ownership;
    private BinStatus binStatus;
    private Integer userId;
}
