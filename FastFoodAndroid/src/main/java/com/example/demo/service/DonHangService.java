package com.example.demo.service;

import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DonHangService {

    public List<DonHang> findAll();

    public DonHang getDonHangById(String id);

    public List<DonHang> getDonHangByKhachHang(String maKhachHang);

    public DonHang addDonHang(DonHang donHang);
    public DonHang updateDonHang(DonHang donHang);

    public boolean huyDonHang(DonHang donHang);
    public boolean setTrangThaiDonHang(String maDonHang, DonHangStatus status);
    public boolean datLaiDonHang(DonHang donHang);

}
