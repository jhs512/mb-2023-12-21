package com.ll.mb.domain.cash.withdraw.controller;

import com.ll.mb.domain.cash.withdraw.entity.WithdrawApply;
import com.ll.mb.domain.cash.withdraw.service.WithdrawService;
import com.ll.mb.domain.global.exceptions.GlobalException;
import com.ll.mb.global.rq.Rq;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/withdraw")
@Controller
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("isAuthenticated()")
public class WithdrawController {
    private final WithdrawService withdrawService;
    private final Rq rq;

    @GetMapping("/applyList")
    public String showApplyList() {
        List<WithdrawApply> withdrawApplies = withdrawService.findByApplicant(rq.getMember());
        rq.attr("withdrawApplies", withdrawApplies);

        return "domain/cash/withdraw/applyList";
    }

    @GetMapping("/apply")
    public String showApply() {
        return "domain/cash/withdraw/apply";
    }

    public record ApplyForm(
            @NotNull long cash,
            @NotBlank String bankName,
            @NotBlank String bankAccountNo
    ) {
    }

    @PostMapping("/apply")
    public String apply(
            @Valid ApplyForm form
    ) {
        if (!withdrawService.canApply(rq.getMember(), form.cash))
            throw new GlobalException("400-1", "출금 신청이 불가능합니다.");

        withdrawService.apply(rq.getMember(), form.cash, form.bankName, form.bankAccountNo);

        return rq.redirect("/withdraw/applyList", "출금 신청이 완료되었습니다.");
    }
}
