package com.vaghelmt.geoapi.controller;

import com.vaghelmt.geoapi.Service.GeolocationService;
import com.vaghelmt.geoapi.model.Geolocation;
import com.vaghelmt.geoapi.model.User;
import com.vaghelmt.geoapi.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${uri}")
    private String uri;

    @Autowired
    GeolocationService geolocationService;

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was successfully registered",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SuccessResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Required fields not provided in request body", content = @Content),
            @ApiResponse(responseCode = "404", description = "User was not registered",content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody User user) {
        Geolocation geolocationData = geolocationService.getGoelocationDetails(user);

        if("Canada".equals(geolocationData.getCountry())){
            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setUuid(UUID.randomUUID());
            successResponse.setMessage("Welcome " + user.getUsername() + " from " + geolocationData.getCity());
            return ResponseEntity.ok(successResponse);

        }

        //TODO: Instead of sending error from here, create a new NotEligibleCountry exception
        // and throw it from here. Then, handle the response from Controller advice
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: User is not eligible to register");

    }
}
