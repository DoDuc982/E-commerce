package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DiscountType {
    @JsonProperty("PERCENTAGE")
    PERCENTAGE,
    @JsonProperty("PRICE")
    PRICE
}
