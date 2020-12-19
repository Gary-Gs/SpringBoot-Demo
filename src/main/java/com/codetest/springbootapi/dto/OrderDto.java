package com.codetest.springbootapi.dto;

import com.codetest.springbootapi.utils.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
public class OrderDto {

    @NotNull(message = Constants.USER_ID_REQUIRED)
    private Long userId;

    @JsonProperty("products")
    @NotEmpty(message = Constants.PRODUCTS_REQUIRED)
    private List<ProductOrderDto> productOrders;

    private Instant dateCreated;
}
