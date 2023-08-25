package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
    @JsonProperty("ADMIN")
    ADMIN,
    @JsonProperty("USER")
    USER
}