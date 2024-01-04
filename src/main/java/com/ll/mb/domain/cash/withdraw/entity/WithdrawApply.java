package com.ll.mb.domain.cash.withdraw.entity;

import com.ll.mb.domain.member.member.entity.Member;
import com.ll.mb.global.jpa.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
public class WithdrawApply extends BaseTime {
    private LocalDateTime withdrawDate;
    private LocalDateTime cancelDate;
    @ManyToOne
    private Member applicant;
    private String bankName;
    private String bankAccountNo;
    private long cash;

    public boolean isWithdrawDone() {
        return withdrawDate != null;
    }
}
