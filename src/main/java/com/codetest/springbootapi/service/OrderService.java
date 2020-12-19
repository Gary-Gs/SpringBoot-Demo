package com.codetest.springbootapi.service;

import com.codetest.springbootapi.dto.OrderDto;

import java.time.Instant;
import java.util.List;

public interface OrderService {
    String PlaceOrder(OrderDto orderDto);

    List<OrderDto> ListOrders(Long userId, Instant fromDate, Instant toDate);
}
