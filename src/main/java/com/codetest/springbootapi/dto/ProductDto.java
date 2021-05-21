package com.codetest.springbootapi.dto;

import com.codetest.springbootapi.constant.Constants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {

    @NotNull(message = Constants.PRODUCT_ID_REQUIRED)
    private Long productId;

    @NotBlank(message = Constants.PRODUCT_NAME_REQUIRED)
    private String productName;

    private String productDescription;

    private BigDecimal price;

    List<String> categories;
}
