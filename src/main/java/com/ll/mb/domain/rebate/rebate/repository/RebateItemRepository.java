package com.ll.mb.domain.rebate.rebate.repository;

import com.ll.mb.domain.rebate.rebate.entity.RebateItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RebateItemRepository extends JpaRepository<RebateItem, Long> {
}
