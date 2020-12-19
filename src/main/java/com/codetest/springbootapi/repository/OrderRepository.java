package com.codetest.springbootapi.repository;

import com.codetest.springbootapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from orders where user_id = ?1 and date_created between ?2 and ?3", nativeQuery = true)
    List<Order> findAllByUserIdAndDateRange(Long userId, Instant fromDate, Instant toDate);
}
