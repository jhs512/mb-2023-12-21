package com.ll.mb.domain.rebate.rebate.repository;

import com.ll.mb.domain.rebate.rebate.entity.RebateItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RebateItemRepository extends JpaRepository<RebateItem, Long> {
    List<RebateItem> findByPayDateBetweenOrderByIdAsc(LocalDateTime fromDate, LocalDateTime toDate);
}
