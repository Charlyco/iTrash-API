package com.ecosmart.manager.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer binId;
    private Double binSize;
    private Location location;
    private Integer userId;
    private BinOwnership ownership;
    @Enumerated(EnumType.STRING)
    private BinStatus binStatus;
}