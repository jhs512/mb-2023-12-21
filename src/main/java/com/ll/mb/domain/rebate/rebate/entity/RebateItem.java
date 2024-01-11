package com.ll.mb.domain.rebate.rebate.entity;

import com.ll.mb.domain.member.member.entity.Member;
import com.ll.mb.domain.product.order.entity.OrderItem;
import com.ll.mb.domain.product.product.entity.Product;
import com.ll.mb.global.jpa.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class RebateItem extends BaseTime {
    private LocalDateTime payDate; // 결제날짜
    private LocalDateTime eventDate; // 판매(구매)가 발생한 날짜
    private LocalDateTime rebateDate; // 정산일
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OrderItem orderItem; // 주문상품
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member seller; // 판매자
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member buyer; // 구매자
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product; // 상품
    private long payPrice; // 결제금액
    private double rebateRate; // 정산율
    private long rebatePrice; // 정산금액

    public boolean isRebateAvailable() {
        return rebateDate == null;
    }

    public void setRebateDone() {
        rebateDate = LocalDateTime.now();
    }
}
