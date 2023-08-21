package com.ecosmart.manager.data;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "agentId")
@DiscriminatorValue("AG")
public class Agent extends User {
    @OneToMany(mappedBy = "agent")
    private List<DisposalRequest> requestListHandled;
    private Location currentLocation;
}
