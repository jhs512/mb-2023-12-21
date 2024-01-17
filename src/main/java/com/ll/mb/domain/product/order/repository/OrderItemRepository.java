package com.ll.mb.domain.product.order.repository;

import com.ll.mb.domain.product.order.entity.OrderItem;
import com.ll.mb.domain.rebate.rebate.entity.RebateItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Page<OrderItem> findByOrderPayDateBetweenAndOrderRefundDateAndRebateItem(
            LocalDateTime startDate,
            LocalDateTime endDate,
            LocalDateTime refundDate,
            RebateItem rebateItem,
            Pageable pageable
    );
}
