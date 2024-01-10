package com.ll.mb.domain.product.order.repository;

import com.ll.mb.domain.product.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderPayDateBetweenAndOrderRefundDateOrderByIdDesc(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime refundDate);
}
