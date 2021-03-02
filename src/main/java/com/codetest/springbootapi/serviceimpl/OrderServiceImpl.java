package com.codetest.springbootapi.serviceimpl;

import com.codetest.springbootapi.dto.OrderDto;
import com.codetest.springbootapi.dto.ProductOrderDto;
import com.codetest.springbootapi.entity.Order;
import com.codetest.springbootapi.entity.Product;
import com.codetest.springbootapi.entity.ProductOrder;
import com.codetest.springbootapi.repository.OrderRepository;
import com.codetest.springbootapi.repository.ProductRepository;
import com.codetest.springbootapi.service.OrderService;
import com.codetest.springbootapi.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String PlaceOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        Set<ProductOrder> productOrderSet = new HashSet<>();

        orderDto.getProductOrders().forEach(productOrder -> {
            Optional<Product> productOptional = productRepository.findById(productOrder.getProductId());
            if (productOptional.isPresent()) {
                ProductOrder po = new ProductOrder();
                po.setQuantity(productOrder.getQuantity());
                po.setProduct(productOptional.get());
                po.setOrder(order);
                productOrderSet.add(po);
            }
        });

        order.setProductOrders(productOrderSet);
        orderRepository.save(order);
        return Constants.ORDER_PLACED;
    }

    @Override
    public List<OrderDto> ListOrders(Long userId, Instant fromDate, Instant toDate) {
        List<Order> orders = orderRepository.findAllByUserIdAndDateRange(userId, fromDate, toDate);
        List<OrderDto> orderDtoList = new ArrayList<>();

        orders.forEach(order -> {
            OrderDto o = new OrderDto();
            o.setDateCreated(order.getDateCreated());
            o.setUserId(order.getUserId());

            List<ProductOrderDto> productOrderDtoList = new ArrayList<>();
            order.getProductOrders().forEach(productOrder -> {
                ProductOrderDto productOrderDto = new ProductOrderDto();
                productOrderDto.setProductId(productOrder.getProduct().getProductId());
                productOrderDto.setProductName(productOrder.getProduct().getProductName());
                productOrderDto.setProductDescription(productOrder.getProduct().getDescription());
                productOrderDto.setPrice(productOrder.getProduct().getPrice());
                productOrderDto.setQuantity(productOrder.getQuantity());
                productOrderDtoList.add(productOrderDto);
            });

            o.setProductOrders(productOrderDtoList);
            orderDtoList.add(o);
        });

        return orderDtoList;
    }

    @Override
    public String Test() {
        return null;
    }
}
