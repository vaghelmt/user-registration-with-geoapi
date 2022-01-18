package com.vaghelmt.geoapi.model;

import com.vaghelmt.geoapi.validators.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    @NotEmpty(message = "IP Address can not be null or empty")
    public String ipAddress;

    @NotEmpty(message = "Username can not be null or empty")
    public String username;

    @NotEmpty(message = "Password can not be null or empty")
    @ValidPassword
    public String password;

}
