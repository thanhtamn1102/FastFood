package com.example.demo.model.places_service_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Access{
    public ArrayList<Double> position;
    public String accessType;
    public String sideOfStreet;
}
