package com.ll.mb.domain.rebate.rebate.controller;

import com.ll.mb.domain.rebate.rebate.service.RebateService;
import com.ll.mb.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adm/rebate")
@RequiredArgsConstructor
public class AdmRebateController {
    private final RebateService rebateService;
    private final Rq rq;

    @GetMapping("/make")
    public String showMake() {
        return "domain/rebate/rebate/adm/make";
    }

    @PostMapping("/make")
    public String make(
            String yearMonth
    ) {
        rebateService.make(yearMonth);

        return rq.redirect("/adm/rebate/make", "정산데이터를 생성했습니다.");
    }
}