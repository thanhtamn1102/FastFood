package com.example.demo.model.places_service_model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Context{
    public Location location;
    public String type;
    public String href;
}
