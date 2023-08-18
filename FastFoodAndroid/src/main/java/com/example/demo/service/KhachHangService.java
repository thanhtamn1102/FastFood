package com.example.demo.service;

import com.example.demo.dto.GioHangItemRequest;
import com.example.demo.model.*;
import com.example.demo.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KhachHangService {

    public List<KhachHang> findAll();
    public KhachHang getKhachHangBySdt(String sdt);
    public KhachHang getKhachHangById(String id);
    public KhachHang insertOne(KhachHang khachHang);
    public KhachHang updateOne(KhachHang khachHang);
    public boolean deleteOne(KhachHang khachHang);
    public boolean themMonAnVaoDSYeuThich(String maKhachHang, String maMonAn);
    public boolean xoaMonAnYeuThich(String maKhachHang, String maMonAn);
    public boolean themMonAnVaoGioHang(String maKhachHang, GioHangItemRequest gioHangItem);
    public boolean themMonAnVaoGioHang(String maKhachHang, List<GioHangItemRequest> gioHangItemRequests);
    public boolean setMonAnTrongGioHang(String maKhachHang, int index, GioHangItemRequest gioHangItemRequest);
    public boolean xoaMonAnKhoiGioHang(String maKhachHang, GioHangItemRequest gioHangItem);
    public boolean clearGioHang(String maKhachHang);
    public boolean setGioHang(String maKhachHang, List<GioHangItem> gioHangItems);
    public DonHang themDonHang(DonHang donHang);
    public List<DonHang> getAllDonHang(String maKhachHang);
    public boolean themDiaChi(String maKhachHang, DiaChi diaChi);
    public boolean xoaDiaChi(String maKhachHang, DiaChi diaChi);
    public boolean capNhatDiaChi(String maKhachHang, String tenDiaChiCu, DiaChi diaChi);
    public boolean themCouponVaoViCoupon(String maKhachHang, String maCoupon);
    public boolean xoaCouponTrongViCoupon(String maKhachHang, String maCoupon);
    public String suDungCoupon(String maKhachHang, String maCoupon);
}
