package com.ll.mb.domain.cash.withdraw.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/withdraw")
@Controller
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("isAuthenticated()")
public class WithdrawController {
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
    @ResponseBody
    public String apply(
            @Valid ApplyForm form
    ) {
        return form.toString();
    }
}
