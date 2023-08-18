package com.example.demo.model.places_service_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Contacts{
    public ArrayList<Phone> phone;
    public ArrayList<Website> website;
}
