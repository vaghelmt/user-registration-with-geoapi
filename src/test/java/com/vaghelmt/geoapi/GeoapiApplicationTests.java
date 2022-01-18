package com.vaghelmt.geoapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaghelmt.geoapi.Service.GeolocationService;
import com.vaghelmt.geoapi.controller.UserController;
import com.vaghelmt.geoapi.model.Geolocation;
import com.vaghelmt.geoapi.model.User;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
class GeoapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeolocationService geolocationService;

    private ObjectMapper mapper = new ObjectMapper();

    private String resource = "/user/register";

    @Test
    public void whenPostRequestWithValidData_thenCorrectResponse() throws Exception {
        String user = "{\"username\" : \"John Doe\",\"password\": \"Pascal@2\", \"ipAddress\" : \"24.48.0.1\"}";
        String geolocationData = "{\"country\" : \"Canada\", \"city\" : \"Montreal\"}";

        Mockito.when(
                geolocationService.getGoelocationDetails(mapper.readValue(user, User.class)))
                .thenReturn(mapper.readValue(geolocationData, Geolocation.class));

        mockMvc.perform(MockMvcRequestBuilders.post(resource)
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is("Welcome John Doe from Montreal")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void whenPostRequestWithInValidUser_thenCorrectResponse() throws Exception {
        String user = "{\"password\": \"Pascal@2\", \"ipAddress\" : \"24.48.0.1.99\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(resource)
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", Is.is("Username can not be null or empty")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
    }


    @Test
    public void whenPostRequestWithInValidPassword_thenCorrectResponse() throws Exception {
        String user = "{\"username\": \"John Doe\",\"password\": \"Pascal\", \"ipAddress\" : \"24.48.0.1\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(resource)
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", Is.is("Password must be 8 or more characters in length.,Password must contain 1 or more special characters.,Password must contain 1 or more digit characters.")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void whenPostRequestWithInValidIpAddress_thenCorrectResponse() throws Exception {
        String user = "{\"password\": \"Pascal@2\", \"username\" : \"John Doe\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(resource)
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors[0]", Is.is("IP Address can not be null or empty")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void whenPostRequestWithCountryOtherThanCanada_thenCorrectResponse() throws Exception {
        String user = "{\"username\" : \"John Doe\",\"password\": \"Pascal@2\", \"ipAddress\" : \"24.48.0.1\"}";
        String geolocationData = "{\"country\" : \"Mexico\", \"city\" : \"Cancun\"}";

        Mockito.when(
                geolocationService.getGoelocationDetails(mapper.readValue(user, User.class)))
                .thenReturn(mapper.readValue(geolocationData, Geolocation.class));

        mockMvc.perform(MockMvcRequestBuilders.post(resource)
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Is.is("Error: User is not eligible to register")));
    }

}
