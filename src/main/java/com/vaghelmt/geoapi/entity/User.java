package com.vaghelmt.geoapi.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

}
