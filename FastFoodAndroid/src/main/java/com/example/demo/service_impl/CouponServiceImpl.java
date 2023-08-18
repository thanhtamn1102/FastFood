package com.example.demo.service_impl;

import com.example.demo.model.Coupon;
import com.example.demo.repository.CouponRepository;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository repository;

    @Override
    public List<Coupon> getAllCoupon() {
        return repository.findAll();
    }

    @Override
    public Coupon getCoupon(String maCoupon) {
        return repository.findById(maCoupon).orElse(null);
    }

    @Override
    public Coupon addCoupon(Coupon coupon) {
        return repository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        return repository.save(coupon);
    }

    @Override
    public boolean deleteCoupon(String maCoupon) {
        try {
            repository.deleteById(maCoupon);
            return true;
        } catch (Exception ex) {
        }
        return false;
    }
}
