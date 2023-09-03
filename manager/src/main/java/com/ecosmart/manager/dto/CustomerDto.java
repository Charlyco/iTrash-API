package com.ecosmart.manager.dto;

import com.ecosmart.manager.data.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CustomerDto {
    private Integer userId;
    private String userName;
    private String fullName;
    private String password;
    private String address;
    private String email;
    private Role role;
    private String phoneNumber;
    private List<Integer> binIds;
    private List<Integer> requestIds;
}
