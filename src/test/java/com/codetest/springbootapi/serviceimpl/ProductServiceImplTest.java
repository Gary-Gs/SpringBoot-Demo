package com.codetest.springbootapi.serviceimpl;

import com.codetest.springbootapi.dto.ProductDto;
import com.codetest.springbootapi.entity.Category;
import com.codetest.springbootapi.entity.Product;
import com.codetest.springbootapi.repository.CategoryRepository;
import com.codetest.springbootapi.repository.ProductRepository;
import com.codetest.springbootapi.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void addProduct_Test() {
        List<Category> categories = new ArrayList<>();
        categories.add(categoryBuilder());

        when(categoryRepository.findAllByCategoryNameIn(any())).thenReturn(categories);

        assertThat(productService.AddProduct(productDtoBuilder())).isEqualTo(Constants.PRODUCT_SAVED);
    }

    @Test
    void listProducts_Test() {
        List<Product> productList = new ArrayList<>();
        productList.add(productBuilder());

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDto> productDtoList = productService.ListProducts();
        assertThat(productDtoList).hasSize(1);
        assertThat(productDtoList.get(0).getProductId()).isEqualTo(productBuilder().getProductId());
        assertThat(productDtoList.get(0).getProductName()).isEqualTo(productBuilder().getProductName());
        assertThat(productDtoList.get(0).getProductDescription()).isEqualTo(productBuilder().getDescription());
        assertThat(productDtoList.get(0).getPrice()).isEqualTo(productBuilder().getPrice());
    }


    private ProductDto productDtoBuilder() {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(1L);
        productDto.setProductName("coke");
        productDto.setProductDescription("coke light");
        productDto.setPrice(new BigDecimal("1.50"));
        List<String> categories = new ArrayList<>();
        categories.add("food");
        productDto.setCategories(categories);
        return productDto;
    }

    private Category categoryBuilder() {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryName("food");
        return category;
    }

    private Product productBuilder() {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("coke");
        product.setDescription("coke light");
        product.setPrice(new BigDecimal("1.50"));
        return product;
    }
}