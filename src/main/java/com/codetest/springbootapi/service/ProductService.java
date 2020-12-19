package com.codetest.springbootapi.service;

import com.codetest.springbootapi.dto.ProductDto;

import java.util.List;

public interface ProductService {
    String AddProduct(ProductDto productDto);

    List<ProductDto> ListProducts();
}
