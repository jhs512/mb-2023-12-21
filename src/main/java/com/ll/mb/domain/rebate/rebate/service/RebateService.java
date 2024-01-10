package com.ll.mb.domain.rebate.rebate.service;

import com.ll.mb.domain.product.order.entity.OrderItem;
import com.ll.mb.domain.rebate.rebate.entity.RebateItem;
import com.ll.mb.domain.rebate.rebate.repository.RebateItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RebateService {
    private final RebateItemRepository rebateItemRepository;

    @Transactional
    public void make(List<OrderItem> orderItems) {
        orderItems
                .stream()
                .forEach(orderItem -> {
                    RebateItem rebateItem = RebateItem.builder()
                            .eventDate(orderItem.getOrder().getCreateDate())
                            .rebateRate(orderItem.getRebateRate())
                            .payPrice(orderItem.getPayPrice())
                            .orderItem(orderItem)
                            .buyer(orderItem.getOrder().getBuyer())
                            .seller(orderItem.getProduct().getMaker())
                            .product(orderItem.getProduct())
                            .build();

                    rebateItemRepository.save(rebateItem);
                });
    }
}