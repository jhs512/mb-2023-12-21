package com.ll.mb.domain.book.purchasedBook.repository;

import com.ll.mb.domain.book.book.entity.Book;
import com.ll.mb.domain.book.purchasedBook.entity.PurchasedBook;
import com.ll.mb.domain.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchasedBookRepository extends JpaRepository<PurchasedBook, Long> {

    Optional<PurchasedBook> findTop1ByOwnerAndBookOrderByIdDesc(Member buyer, Book book);
}
