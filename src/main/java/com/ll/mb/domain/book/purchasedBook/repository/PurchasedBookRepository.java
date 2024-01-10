package com.ll.mb.domain.book.purchasedBook.repository;

import com.ll.mb.domain.book.purchasedBook.entity.PurchasedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedBookRepository extends JpaRepository<PurchasedBook, Long> {

}
