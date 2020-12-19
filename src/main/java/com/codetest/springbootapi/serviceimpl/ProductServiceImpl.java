package com.codetest.springbootapi.serviceimpl;

import com.codetest.springbootapi.dto.ProductDto;
import com.codetest.springbootapi.repository.CategoryRepository;
import com.codetest.springbootapi.repository.ProductRepository;
import com.codetest.springbootapi.entity.Category;
import com.codetest.springbootapi.entity.Product;
import com.codetest.springbootapi.service.ProductService;
import com.codetest.springbootapi.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String AddProduct(ProductDto productDto) {
        List<Category> categories = categoryRepository.findAllByCategoryNameIn(productDto.getCategories());

        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getProductDescription());
        product.setPrice(productDto.getPrice());
        product.setCategories(new HashSet<>(categories));

        productRepository.save(product);
        return Constants.PRODUCT_SAVED;
    }

    @Override
    public List<ProductDto> ListProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        products.forEach(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setProductDescription(product.getDescription());
            productDto.setPrice(product.getPrice());

            List<String> categories = new ArrayList<>();
            for (Category c : product.getCategories()) {
                categories.add(c.getCategoryName());
            }

            productDto.setCategories(categories);
            productDtoList.add(productDto);
        });
        return productDtoList;
    }
}
