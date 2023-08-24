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
@PrimaryKeyJoinColumn(name="agentId")
@DiscriminatorValue("AG")
public class Agent extends User {
    @OneToMany(mappedBy="agent")
    private List<DisposalRequest> requestListHandled;
    @Embedded
    private Location currentLocation;
}
