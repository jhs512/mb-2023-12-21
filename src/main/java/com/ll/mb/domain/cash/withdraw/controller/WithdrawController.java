package com.ll.mb.domain.cash.withdraw.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/withdraw")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("isAuthenticated()")
public class WithdrawController {
    @GetMapping("/apply")
    public String showApply() {
        return "domain/cash/withdraw/apply";
    }
}
