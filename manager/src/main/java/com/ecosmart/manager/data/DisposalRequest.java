package com.ecosmart.manager.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DisposalRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;
    private RequestStatus requestStatus;
    @OneToOne
    private Bin bin;

    @ManyToOne
    private Customer customer;
    private LocalDateTime requestDate;
    @ManyToOne
    private Agent agent;

}
