package com.ll.mb.domain.book.purchasedBook.service;

import com.ll.mb.domain.book.book.entity.Book;
import com.ll.mb.domain.book.purchasedBook.entity.PurchasedBook;
import com.ll.mb.domain.book.purchasedBook.repository.PurchasedBookRepository;
import com.ll.mb.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PurchasedBookService {
    private final PurchasedBookRepository purchasedBookRepository;

    @Transactional
    public PurchasedBook add(Member buyer, Book book) {
        PurchasedBook purchasedBook = PurchasedBook.builder()
                .owner(buyer)
                .book(book)
                .build();

        purchasedBookRepository.save(purchasedBook);

        return purchasedBook;
    }

    @Transactional
    public void delete(Member buyer, Book book) {
        purchasedBookRepository.findTop1ByOwnerAndBookOrderByIdDesc(buyer, book)
                .ifPresent(purchasedBookRepository::delete);
    }
}
