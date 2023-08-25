package com.example.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DiscountFor {

    @JsonProperty("ORDER")
    ORDER,
    @JsonProperty("PRODUCT")
    PRODUCT
}
