package com.ll.mb.domain.product.order.entity;

import com.ll.mb.domain.product.product.entity.Product;
import com.ll.mb.domain.rebate.rebate.entity.RebateItem;
import com.ll.mb.global.jpa.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class OrderItem extends BaseTime {
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private double rebateRate;
    private long payPrice;
    @ManyToOne
    private RebateItem rebateItem;

    public void setPaymentDone() {
        switch (product.getRelTypeCode()) {
            case "book" -> order.getBuyer().addMyBook(product.getBook());
        }
    }

    public void setCancelDone() {
    }

    public void setRefundDone() {
        switch (product.getRelTypeCode()) {
            case "book" -> order.getBuyer().removeMyBook(product.getBook());
        }
    }
}
