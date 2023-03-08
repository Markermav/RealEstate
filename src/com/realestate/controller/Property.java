package com.realestate.controller;

import java.util.ArrayList;

public interface Property {
    ArrayList<Object> properties = new ArrayList<>();

    default void addProperty(Object property) {
        properties.add(property);
    }

    default ArrayList<Object> getProperties() {
        return properties;
    }
}

