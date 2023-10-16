package com.example.eindopdracht;

import com.example.eindopdracht.models.Property;

public class Main  {
    public static void main(String[] args) {
        Property myProperty = new Property("Hoofdstraat", 3);
        System.out.println("Address: " + myProperty.getStreetName());
        System.out.println("Number of Rooms: " + myProperty.getHouseNumber());
    }
}
