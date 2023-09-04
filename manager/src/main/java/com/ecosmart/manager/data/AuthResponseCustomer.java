package com.ecosmart.manager.data;

import com.ecosmart.manager.dto.CustomerDto;
import com.ecosmart.manager.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponseCustomer {
    private String token;
    private CustomerDto customer;
}
