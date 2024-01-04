package com.ll.mb.domain.cash.withdraw.service;

import com.ll.mb.domain.cash.withdraw.entity.WithdrawApply;
import com.ll.mb.domain.cash.withdraw.repository.WithdrawApplyRepository;
import com.ll.mb.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<WithdrawApply> findById(long id) {
        return withdrawApplyRepository.findById(id);
    }

    public boolean canDelete(Member actor, WithdrawApply withdrawApply) {
        if (actor.isAdmin()) return true;

        if (!withdrawApply.getApplicant().equals(actor)) return false;

        if (withdrawApply.isWithdrawDone()) return false;

        return true;
    }

    @Transactional
    public void delete(WithdrawApply withdrawApply) {
        withdrawApplyRepository.delete(withdrawApply);
    }

    public List<WithdrawApply> findAll() {
        return withdrawApplyRepository.findAllByOrderByIdDesc();
    }
}
