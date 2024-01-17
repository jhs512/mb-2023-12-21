package com.ll.mb.domain.rebate.rebate.controller;

import com.ll.mb.domain.global.exceptions.GlobalException;
import com.ll.mb.domain.rebate.rebate.entity.RebateItem;
import com.ll.mb.domain.rebate.rebate.service.RebateBatchService;
import com.ll.mb.domain.rebate.rebate.service.RebateService;
import com.ll.mb.global.rq.Rq;
import com.ll.mb.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adm/rebate")
@RequiredArgsConstructor
public class AdmRebateController {
    private final RebateService rebateService;
    private final RebateBatchService rebateBatchService;
    private final Rq rq;

    @GetMapping("/make")
    public String showMake() {
        return "domain/rebate/rebate/adm/make";
    }

    @PostMapping("/make")
    public String make(
            String yearMonth
    ) {
        rebateBatchService.make(yearMonth);

        return rq.redirect("/adm/rebate/make", "정산데이터를 생성했습니다.");
    }

    @GetMapping("/list")
    public String showList(String yearMonth, Model model) {
        if (Ut.str.isBlank(yearMonth)) {
            yearMonth = Ut.date.getCurrentYearMonth();
        }

        List<RebateItem> items = rebateService.findByPayDateIn(yearMonth);

        model.addAttribute("yearMonth", yearMonth);
        model.addAttribute("items", items);

        return "domain/rebate/rebate/adm/list";
    }

    @PostMapping("/{id}/rebate")
    public String rebate(
            @PathVariable long id,
            String redirectUrl
    ) {
        RebateItem rebateItem = rebateService.findById(id).orElseThrow(() -> new GlobalException("400", "정산데이터가 존재하지 않습니다."));

        rebateService.rebate(rebateItem);

        return rq.redirect(redirectUrl, "%d번 정산데이터를 처리하였습니다.".formatted(rebateItem.getId()));
    }

    @PostMapping("/rebateMany")
    public String rebateMany(
            @RequestParam List<Long> ids,
            String redirectUrl
    ) {
        rebateService.rebate(ids);

        return rq.redirect(redirectUrl, "정산데이터를 처리하였습니다.");
    }
}