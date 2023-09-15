package com.ecosmart.manager.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name="customerId")
@DiscriminatorValue("CU")
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<Bin> binList;

    @OneToMany(mappedBy = "customer")
    private List<DisposalRequest> requests;
    @OneToMany(mappedBy = "customer")
    private List<BinRequest> binRequests;
}
