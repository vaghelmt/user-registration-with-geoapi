package com.vaghelmt.geoapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Geolocation {

    @JsonProperty("status")
    public String status;

    @JsonProperty("country")
    public String country;

    @JsonProperty("countryCode")
    public String countryCode;

    @JsonProperty("region")
    public String region;

    @JsonProperty("regionName")
    public String regionName;

    @JsonProperty("city")
    public String city;

    @JsonProperty("zip")
    public String zip;

    @JsonProperty("lat")
    public Double lat;

    @JsonProperty("lon")
    public Double lon;

    @JsonProperty("timezone")
    public String timezone;

    @JsonProperty("isp")
    public String isp;

    @JsonProperty("org")
    public String org;

    @JsonProperty("as")
    public String as;

    @JsonProperty("query")
    public String query;

}
