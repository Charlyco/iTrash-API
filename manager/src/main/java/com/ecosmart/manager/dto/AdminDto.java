package com.ecosmart.manager.dto;

import com.ecosmart.manager.data.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
    private Integer userId;
    private String userName;
    private String password;
    private String address;
    private String email;
    private Role role;
    private String phoneNumber;
}
