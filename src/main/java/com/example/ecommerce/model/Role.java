package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Iterator;

public enum Role{
    @JsonProperty("ADMIN")
    ADMIN,
    @JsonProperty("USER")
    USER;

}