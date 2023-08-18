package com.example.demo.controller;

import com.example.demo.dto.GioHangItemRequest;
import com.example.demo.model.DiaChi;
import com.example.demo.model.DonHang;
import com.example.demo.model.GioHangItem;
import com.example.demo.model.KhachHang;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fastfood/api/khachhang")
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(khachHangService.findAll());
    }

    @GetMapping("/{sdt}")
    public ResponseEntity<?> getKhachHangBySdt(@PathVariable(value = "sdt") String sdt) {
        KhachHang khachHang = khachHangService.getKhachHangBySdt(sdt);
        return ResponseEntity.ok(khachHang != null ? khachHang : new KhachHang());
    }

    @PostMapping("")
    public ResponseEntity<?> insertOne(@RequestBody KhachHang khachHang) {
        return ResponseEntity.ok(khachHangService.insertOne(khachHang));
    }

    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody KhachHang khachHang) {
        return ResponseEntity.ok(khachHangService.updateOne(khachHang));
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteOne(@RequestBody KhachHang khachHang) {
        return ResponseEntity.ok(khachHangService.deleteOne(khachHang));
    }

    @PutMapping("dsmonanyeuthich/add")
    public ResponseEntity<?> themMonAnVaoDSYeuThich(@RequestParam(value = "makhachhang") String maKhachHang, @RequestParam("mamonan") String maMonAn) {
        return ResponseEntity.ok(khachHangService.themMonAnVaoDSYeuThich(maKhachHang, maMonAn));
    }

    @PutMapping("dsmonanyeuthich/remove")
    public ResponseEntity<?> xoaMonAnYeuThich(@RequestParam(value = "makhachhang") String maKhachHang, @RequestParam("mamonan") String maMonAn) {
        return ResponseEntity.ok(khachHangService.xoaMonAnYeuThich(maKhachHang, maMonAn));
    }

    @PutMapping("giohang/add")
    public ResponseEntity<?> themMonAnVaoGioHang(@RequestParam(value = "makhachhang") String maKhachHang,
                                                 @RequestBody GioHangItemRequest gioHangItem) {
        return ResponseEntity.ok(khachHangService.themMonAnVaoGioHang(maKhachHang, gioHangItem));
    }

    @PutMapping("giohang/addall")
    public ResponseEntity<?> themMonAnVaoGioHang(@RequestParam(value = "makhachhang") String maKhachHang,
                                                 @RequestBody List<GioHangItemRequest> gioHangItemRequests) {
        return ResponseEntity.ok(khachHangService.themMonAnVaoGioHang(maKhachHang, gioHangItemRequests));
    }

    @PutMapping("giohang/set")
    public ResponseEntity<?> setMonAnTrongGioHang(@RequestParam(value = "makhachhang") String maKhachHang,
                                                  @RequestParam(value = "index") int index,
                                                  @RequestBody GioHangItemRequest gioHangItemRequest) {
        return ResponseEntity.ok(khachHangService.setMonAnTrongGioHang(maKhachHang, index, gioHangItemRequest));
    }

    @PutMapping("giohang/remove")
    public ResponseEntity<?> xoaMonAnKhoiGioHang(@RequestParam(value = "makhachhang") String maKhachHang,
                                                 @RequestBody GioHangItemRequest gioHangItemRequest) {
        return ResponseEntity.ok(khachHangService.xoaMonAnKhoiGioHang(maKhachHang, gioHangItemRequest));
    }

    @PutMapping("giohang/clear/{makhachhang}")
    public ResponseEntity<?> clearGioHang(@PathVariable("makhachhang") String maKhachHang) {
        return ResponseEntity.ok(khachHangService.clearGioHang(maKhachHang));
    }

    @PutMapping("giohang/set/{maKhachHang}")
    public ResponseEntity<?> setGioHang(@PathVariable("maKhachHang") String maKhachHang,
                                        @RequestBody List<GioHangItem> gioHangItems) {
        return ResponseEntity.ok(khachHangService.setGioHang(maKhachHang, gioHangItems));
    }

    @GetMapping("donhang/{makhachhang}")
    public ResponseEntity<?> getAllDonHang(@PathVariable("makhachhang") String maKhachHang) {
        return ResponseEntity.ok(khachHangService.getAllDonHang(maKhachHang));
    }

    @PutMapping("donhang/add")
    public ResponseEntity<?> themDonHang(@RequestBody DonHang donHang) {
        return ResponseEntity.ok(khachHangService.themDonHang(donHang));
    }

    @PutMapping("sodiachi/add")
    public ResponseEntity<?> themDiaChi(@RequestParam(value = "makhachhang") String maKhachHang,
                                        @RequestBody DiaChi diaChi) {
        return ResponseEntity.ok(khachHangService.themDiaChi(maKhachHang, diaChi));
    }

    @PutMapping("sodiachi/remove")
    public ResponseEntity<?> xoaDiaChi(@RequestParam(value = "makhachhang") String maKhachHang,
                                       @RequestBody DiaChi diaChi) {
        return ResponseEntity.ok(khachHangService.xoaDiaChi(maKhachHang, diaChi));
    }

    @PutMapping("sodiachi/update")
    public ResponseEntity<?> capNhatDiaChi(@RequestParam(value = "makhachhang") String maKhachHang,
                                           @RequestParam(value = "tendiachicu") String tenDiaChiCu,
                                           @RequestBody DiaChi diaChi) {
        return ResponseEntity.ok(khachHangService.capNhatDiaChi(maKhachHang, tenDiaChiCu, diaChi));
    }

    @PutMapping("vicoupon/add")
    public ResponseEntity<?> themCouponVaoViCoupon(@RequestParam(value = "makhachhang") String maKhachHang,
                                                   @RequestParam(value = "macoupon") String maCoupon) {
        return ResponseEntity.ok(khachHangService.themCouponVaoViCoupon(maKhachHang, maCoupon));
    }

    @PutMapping("vicoupon/remove")
    public ResponseEntity<?> xoaCouponTrongViCoupon(@RequestParam(value = "makhachhang") String maKhachHang,
                                                    @RequestParam(value = "macoupon") String maCoupon) {
        return ResponseEntity.ok(khachHangService.xoaCouponTrongViCoupon(maKhachHang, maCoupon));
    }

    @PutMapping("vicoupon/use")
    public ResponseEntity<?> suDungCoupon(@RequestParam(value = "makhachhang") String maKhachHang,
                                          @RequestParam(value = "macoupon") String maCoupon) {
        return ResponseEntity.ok(khachHangService.suDungCoupon(maKhachHang, maCoupon));
    }

}
