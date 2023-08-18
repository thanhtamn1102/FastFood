package com.example.demo.model.places_service_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Address{
    public String text;
    public String house;
    public String street;
    public String district;
    public String city;
    public String country;
    public String countryCode;
    public String postalCode;
    public String county;
    public String stateCode;
}
