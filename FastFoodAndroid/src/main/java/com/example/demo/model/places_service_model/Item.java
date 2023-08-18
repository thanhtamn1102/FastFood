package com.example.demo.model.places_service_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
public class Item{
    public ArrayList<Double> position;
    public int distance;
    public String title;
    public double averageRating;
    public Category category;
    public String icon;
    public String vicinity;
    public Address address;
    public Contacts contacts;
    public ArrayList<Object> having;
    public String type;
    public String href;
    public String id;
    public boolean authoritative;
    public ArrayList<Access> access;
    public ArrayList<AlternativeName> alternativeNames;
}
