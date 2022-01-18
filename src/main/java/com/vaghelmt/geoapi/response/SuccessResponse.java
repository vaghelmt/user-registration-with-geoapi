package com.vaghelmt.geoapi.response;

import lombok.Data;

import java.util.UUID;

@Data
public class SuccessResponse {

    private UUID uuid;
    private String message;

}
