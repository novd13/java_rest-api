package cz.enehano.training.demoapp.restapi.dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;

    // you can add another attributes like address, email, phone etc.
}
