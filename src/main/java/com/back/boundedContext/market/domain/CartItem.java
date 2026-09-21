package com.back.boundedContext.market.domain;

import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "cart_item")
public class CartItem extends BaseIdAndTime {

    @ManyToOne(fetch = LAZY)
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    private Product product;
}
