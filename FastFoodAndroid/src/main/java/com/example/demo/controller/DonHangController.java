package com.example.demo.controller;

import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangStatus;
import com.example.demo.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fastfood/api/donhang")
public class DonHangController {

    @Autowired
    private DonHangService donHangService;

    @GetMapping("/getdonhangbykhachhang/{makhachhang}")
    public ResponseEntity<?> getDonHangByKhachHang(@PathVariable("makhachhang") String maKhachHang) {
        return ResponseEntity.ok(donHangService.getDonHangByKhachHang(maKhachHang));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getDonHangById(@PathVariable("id") String id) {
        return ResponseEntity.ok(donHangService.getDonHangById(id));
    }

    @PutMapping("huydonhang")
    public ResponseEntity<?> huyDonHang(@RequestBody DonHang donHang) {
        return ResponseEntity.ok(donHangService.huyDonHang(donHang));
    }

    @PutMapping("settrangthai/{maDonHang}")
    public ResponseEntity<?> setTrangThaiDonHang(@PathVariable("maDonHang") String maDonHang, @RequestBody DonHangStatus status) {
        return ResponseEntity.ok(donHangService.setTrangThaiDonHang(maDonHang, status));
    }

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(donHangService.findAll());
    }


}
