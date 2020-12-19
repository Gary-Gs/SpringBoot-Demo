package com.codetest.springbootapi.controller;

import com.codetest.springbootapi.dto.ProductDto;
import com.codetest.springbootapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/v1/products", method = RequestMethod.POST)
    public String addProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.AddProduct(productDto);
    }

    @RequestMapping(value = "/v1/products", method = RequestMethod.GET)
    public List<ProductDto> listProducts() {
        return productService.ListProducts();
    }
}
