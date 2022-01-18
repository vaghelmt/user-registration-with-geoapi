package com.vaghelmt.geoapi.Service;

import com.vaghelmt.geoapi.model.Geolocation;
import com.vaghelmt.geoapi.model.User;
import com.vaghelmt.geoapi.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeolocationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${uri}")
    private String uri;


    public Geolocation getGoelocationDetails(User user){
        return restTemplate.getForObject(uri + user.getIpAddress(), Geolocation.class);
    }

}
