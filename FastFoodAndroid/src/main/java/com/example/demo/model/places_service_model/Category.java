package com.example.demo.model.places_service_model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Category{
    public String id;
    public String title;
    public String href;
    public String type;
    public String system;
}
