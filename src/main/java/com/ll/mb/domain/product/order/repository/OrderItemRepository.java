package com.ll.mb.domain.product.order.repository;

import com.ll.mb.domain.product.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
