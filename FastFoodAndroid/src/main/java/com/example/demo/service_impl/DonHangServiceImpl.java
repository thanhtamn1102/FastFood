package com.example.demo.service_impl;

import com.example.demo.model.DonHang;
import com.example.demo.model.DonHangStatus;
import com.example.demo.model.KhachHang;
import com.example.demo.repository.DonHangRepository;
import com.example.demo.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository repository;

    @Override
    public List<DonHang> findAll() {
        return repository.findAll();
    }

    @Override
    public DonHang getDonHangById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<DonHang> getDonHangByKhachHang(String maKhachHang) {
        List<DonHang> dsDonHang = findAll();
        List<DonHang> result = new ArrayList<>();
        for(DonHang donHang : dsDonHang) {
            if(donHang.getMaKhachHang().equals(maKhachHang))
                result.add(donHang);
        }
        return result;
    }

    @Override
    public DonHang addDonHang(DonHang donHang) {
        return repository.save(donHang);
    }

    public DonHang updateDonHang(DonHang donHang) {
        return repository.save(donHang);
    }

    @Override
    public boolean huyDonHang(DonHang donHang) {
        donHang.setTrangThai(DonHangStatus.DA_HUY);
        if(updateDonHang(donHang) != null)
            return true;
        return false;
    }

    @Override
    public boolean setTrangThaiDonHang(String maDonHang, DonHangStatus status) {
        DonHang donHang = getDonHangById(maDonHang);
        if(donHang != null) {
            donHang.setTrangThai(status);
            if(updateDonHang(donHang) != null)
                return true;
        }
        return false;
    }

    @Override
    public boolean datLaiDonHang(DonHang donHang) {

        return false;
    }
}
