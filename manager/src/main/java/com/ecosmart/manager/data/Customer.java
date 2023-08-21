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
@PrimaryKeyJoinColumn(name = "customerId")
@DiscriminatorValue("CU")
@Entity
public class Customer extends User {
    private List<Bin> binList;

    @OneToMany(mappedBy = "user")
    private List<DisposalRequest> requests;
}
