package com.ecosmart.manager.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class BinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;
    private BinSize binSize;
    @ManyToOne
    private Customer customer;
    private Location location;
    private String detailedAddress;

    @Enumerated(EnumType.STRING)
    private BinOwnership binOwnership;

    @Enumerated(EnumType.STRING)
    private BinRequestStatus requestStatus;
    private LocalDateTime requestDate;
}
