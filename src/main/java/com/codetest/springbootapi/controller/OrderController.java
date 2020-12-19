package com.codetest.springbootapi.controller;

import com.codetest.springbootapi.dto.OrderDto;
import com.codetest.springbootapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/v1/orders", method = RequestMethod.POST)
    public String placeOrder(@Valid @RequestBody OrderDto orderDto) {
        return orderService.PlaceOrder(orderDto);
    }

    @RequestMapping(value = "/v1/orders/users/{userId}", method = RequestMethod.GET)
    public List<OrderDto> listOrders(@PathVariable @NotNull Long userId, @RequestParam(name = "fromDate") Instant fromDate, @RequestParam(name = "toDate") Instant toDate) {
        return orderService.ListOrders(userId, fromDate, toDate);
    }

}
