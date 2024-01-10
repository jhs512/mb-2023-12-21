package com.ll.mb.domain.product.product.entity;

import com.ll.mb.domain.book.book.entity.Book;
import com.ll.mb.domain.member.member.entity.Member;
import com.ll.mb.global.app.AppConfig;
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
public class Product extends BaseTime {
    @ManyToOne
    private Member maker;
    private String relTypeCode;
    private long relId;
    private String name;
    private long price;
    private boolean published;

    public boolean isBook() {
        return relTypeCode.equals("book");
    }

    public Book getBook() {
        return AppConfig.getEntityManager().getReference(Book.class, relId);
    }
}
