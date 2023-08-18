package com.example.demo.controller;

import com.example.demo.model.Coupon;
import com.example.demo.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fastfood/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("")
    public ResponseEntity<?> getAllCoupon() {
        return ResponseEntity.ok(couponService.getAllCoupon());
    }

    @GetMapping("/{maCoupon}")
    public ResponseEntity<?> getCoupon(@PathVariable("maCoupon") String maCoupon) {
        return ResponseEntity.ok(couponService.getCoupon(maCoupon));
    }

    @PostMapping("")
    public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.addCoupon(coupon));
    }

    @PutMapping("")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) {
        return ResponseEntity.ok(couponService.updateCoupon(coupon));
    }

    @DeleteMapping("/{maCoupon}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("maCoupon") String maCoupon) {
        return ResponseEntity.ok(couponService.deleteCoupon(maCoupon));
    }
}
