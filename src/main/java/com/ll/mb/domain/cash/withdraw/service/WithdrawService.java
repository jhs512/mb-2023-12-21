package com.ll.mb.domain.cash.withdraw.service;

import com.ll.mb.domain.cash.withdraw.entity.WithdrawApply;
import com.ll.mb.domain.cash.withdraw.repository.WithdrawApplyRepository;
import com.ll.mb.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WithdrawService {
    private final WithdrawApplyRepository withdrawApplyRepository;

    public boolean canApply(Member actor, long cash) {
        return actor.getRestCash() >= cash;
    }

    @Transactional
    public void apply(Member applicant, long cash, String bankName, String bankAccountNo) {
        WithdrawApply apply = WithdrawApply.builder()
                .applicant(applicant)
                .cash(cash)
                .bankName(bankName)
                .bankAccountNo(bankAccountNo)
                .build();

        withdrawApplyRepository.save(apply);
    }

    public List<WithdrawApply> findByApplicant(Member applicant) {
        return withdrawApplyRepository.findByApplicantOrderByIdDesc(applicant);
    }
}
