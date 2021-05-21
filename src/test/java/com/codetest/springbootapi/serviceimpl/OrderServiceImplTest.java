package com.codetest.springbootapi.serviceimpl;

import com.codetest.springbootapi.dto.OrderDto;
import com.codetest.springbootapi.dto.ProductOrderDto;
import com.codetest.springbootapi.entity.Order;
import com.codetest.springbootapi.entity.Product;
import com.codetest.springbootapi.entity.ProductOrder;
import com.codetest.springbootapi.repository.OrderRepository;
import com.codetest.springbootapi.repository.ProductRepository;
import com.codetest.springbootapi.constant.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void placeOrder_Test() {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1L);
        List<ProductOrderDto> productOrderDtoList = new ArrayList<>();
        productOrderDtoList.add(productOrderDtoBuilder());
        orderDto.setProductOrders(productOrderDtoList);

        when(productRepository.findById(any())).thenReturn(Optional.of(productBuilder()));
        when(orderRepository.save(any(Order.class))).thenReturn(null);

        assertThat(orderService.PlaceOrder(orderDto)).isEqualTo(Constants.ORDER_PLACED);
    }

    @Test
    void listOrders_Test() {
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setOrderId(1L);
        order.setUserId(1L);
        order.setDateCreated(Instant.now());
        Set<ProductOrder> productOrderSet = new HashSet<>();
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(productBuilder());
        productOrder.setQuantity(5);
        productOrderSet.add(productOrder);
        order.setProductOrders(productOrderSet);
        orderList.add(order);

        when(orderRepository.findAllByUserIdAndDateRange(any(), any(), any())).thenReturn(orderList);

        List<OrderDto> orderDtos = orderService.ListOrders(1L, Instant.now(), Instant.now());
        assertThat(orderDtos).hasSize(1);
        assertThat(orderDtos.get(0).getUserId()).isEqualTo(orderList.get(0).getUserId());
        assertThat(orderDtos.get(0).getProductOrders().get(0).getProductId()).isEqualTo(productBuilder().getProductId());
        assertThat(orderDtos.get(0).getProductOrders().get(0).getProductName()).isEqualTo(productBuilder().getProductName());
        assertThat(orderDtos.get(0).getProductOrders().get(0).getProductDescription()).isEqualTo(productBuilder().getDescription());
        assertThat(orderDtos.get(0).getProductOrders().get(0).getPrice()).isEqualTo(productBuilder().getPrice());
    }

    private ProductOrderDto productOrderDtoBuilder() {
        ProductOrderDto productOrder = new ProductOrderDto();
        productOrder.setProductId(1L);
        productOrder.setPrice(new BigDecimal("12.10"));
        productOrder.setProductName("cake");
        productOrder.setProductDescription("chocolate cake");
        productOrder.setQuantity(5);
        return productOrder;
    }

    private Product productBuilder() {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("cake");
        product.setDescription("chocolate cake");
        product.setPrice(new BigDecimal("12.10"));
        return product;
    }
}