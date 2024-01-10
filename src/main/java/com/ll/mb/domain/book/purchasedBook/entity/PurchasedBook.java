package com.ll.mb.domain.book.purchasedBook.entity;

import com.ll.mb.domain.book.book.entity.Book;
import com.ll.mb.domain.member.member.entity.Member;
import com.ll.mb.global.jpa.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class PurchasedBook extends BaseTime {
    @ManyToOne
    private Member owner;
    @ManyToOne
    private Book book;
}
