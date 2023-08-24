package com.ecosmart.manager.data;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Location{
    Double latitude;
    Double longitude;
}
