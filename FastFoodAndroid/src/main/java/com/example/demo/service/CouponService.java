package com.example.demo.service;

import com.example.demo.model.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CouponService {

    public List<Coupon> getAllCoupon();
    public Coupon getCoupon(String maCoupon);
    public Coupon addCoupon(Coupon coupon);
    public Coupon updateCoupon(Coupon coupon);
    public boolean deleteCoupon(String maCoupon);

}
