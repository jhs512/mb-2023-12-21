package com.ll.mb.domain.rebate.rebate.scheduler;

import com.ll.mb.domain.rebate.rebate.service.RebateBatchService;
import com.ll.mb.global.app.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class RebateScheduler {
    private final RebateBatchService rebateBatchService;

    // 매월 15일 1시에 자동실행
    @Scheduled(cron = "0 0 1 15 * *") // 운영
    // @Scheduled(cron = "0 * * * * *") // 테스트
    public void runMakeRebateData() {
        if (AppConfig.isNotProd()) return;

        String yearMonth = LocalDateTime.now().minusMonths(1).toString().substring(0, 7);
        rebateBatchService.make(yearMonth);
    }
}
