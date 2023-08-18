package com.example.demo.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChiTietViCoupon implements Serializable {

    @DocumentReference
    @EqualsAndHashCode.Include
    private Coupon coupon;
    private int soLuongDaSuDung;

    public ChiTietViCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
