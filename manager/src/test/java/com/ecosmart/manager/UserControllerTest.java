package com.ecosmart.manager;

import com.ecosmart.manager.controller.UserController;
import com.ecosmart.manager.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserControllerTest {

//    @Autowired
//    UserController userController;
//
//    @Test
//    void shouldLoadCustomerByUserName() {
//        ResponseEntity<CustomerDto> customer = userController.loadCustomerDetails("Charles12");
//        assertThat(customer.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(Objects.requireNonNull(customer.getBody()).getUserName()).isEqualTo("Charles12");
//    }
}
