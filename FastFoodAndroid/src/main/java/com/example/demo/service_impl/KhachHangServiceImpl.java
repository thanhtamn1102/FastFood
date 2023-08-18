package com.example.demo.service_impl;

import com.example.demo.dto.GioHangItemRequest;
import com.example.demo.mapper.GioHangItemMapper;
import com.example.demo.model.*;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.service.CouponService;
import com.example.demo.service.DonHangService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.MonAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository repository;
    @Autowired
    private MonAnService monAnService;
    @Autowired
    private DonHangService donHangService;
    @Autowired
    private CouponService couponService;

    @Override
    public List<KhachHang> findAll() {
        return repository.findAll();
    }

    @Override
    public KhachHang getKhachHangBySdt(String sdt) {
        return repository.findBySdt(sdt);
    }

    @Override
    public KhachHang getKhachHangById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public KhachHang insertOne(KhachHang khachHang) {
        return repository.save(khachHang);
    }

    @Override
    public KhachHang updateOne(KhachHang khachHang) {
        return repository.save(khachHang);
    }

    @Override
    public boolean deleteOne(KhachHang khachHang) {
        try {
            repository.delete(khachHang);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean themMonAnVaoDSYeuThich(String maKhachHang, String maMonAn) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            MonAn monAn = monAnService.findById(maMonAn);
            khachHang.getDsMonAnYeuThich().add(monAn);
            if(updateOne(khachHang) != null)
                return true;
        }
        return false;
    }

    @Override
    public boolean xoaMonAnYeuThich(String maKhachHang, String maMonAn) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            MonAn monAn = new MonAn();
            monAn.setMaMonAn(maMonAn);

            if(khachHang.getDsMonAnYeuThich().remove(monAn)
                && updateOne(khachHang) != null)
                return true;
        }
        return false;
    }

    @Override
    public boolean themMonAnVaoGioHang(String maKhachHang, GioHangItemRequest gioHangItemRequest) {
        GioHangItem gioHangItem = toGioHangItem(gioHangItemRequest);
        KhachHang khachHang = getKhachHangById(maKhachHang);

        if(khachHang != null) {
            int index = khachHang.getGioHang().indexOf(gioHangItem);
            if(index >= 0) {
                khachHang.getGioHang().get(index).setSoLuong(
                        khachHang.getGioHang().get(index).getSoLuong() + gioHangItem.getSoLuong()
                );
                if(updateOne(khachHang) != null)
                    return true;
            } else {
                if(khachHang.getGioHang().add(gioHangItem) && updateOne(khachHang) != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean themMonAnVaoGioHang(String maKhachHang, List<GioHangItemRequest> gioHangItemRequests) {
        gioHangItemRequests.forEach(gioHangItemRequest -> {
            themMonAnVaoGioHang(maKhachHang, gioHangItemRequest);
        });
        return true;
    }

    @Override
    public boolean setMonAnTrongGioHang(String maKhachHang, int index, GioHangItemRequest gioHangItemRequest) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
//            GioHangItem gioHangItem = toGioHangItem(gioHangItemRequest);
            khachHang.getGioHang().remove(index);
//            khachHang.getGioHang().set(index, gioHangItem);
            if(updateOne(khachHang) != null && themMonAnVaoGioHang(maKhachHang, gioHangItemRequest))
                return true;
        }
        return false;
    }

    @Override
    public boolean xoaMonAnKhoiGioHang(String maKhachHang, GioHangItemRequest gioHangItemRequest) {
        GioHangItem gioHangItem = toGioHangItem(gioHangItemRequest);
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            if(khachHang.getGioHang().remove(gioHangItem) && updateOne(khachHang) != null)
                return true;
        }
        return false;
    }

    @Override
    public boolean clearGioHang(String maKhachHang) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            khachHang.getGioHang().clear();
            updateOne(khachHang);
            return true;
        }
        return false;
    }

    @Override
    public boolean setGioHang(String maKhachHang, List<GioHangItem> gioHangItems) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            khachHang.getGioHang().clear();
            khachHang.getGioHang().addAll(gioHangItems);
            if(updateOne(khachHang) != null)
                return true;
        }
        return false;
    }

    private GioHangItem toGioHangItem(GioHangItemRequest gioHangItemRequest) {
        GioHangItem gioHangItem = new GioHangItem();
        gioHangItem.setMonAn(monAnService.findById(gioHangItemRequest.getMaMonAn()));
        gioHangItem.setSoLuong(gioHangItemRequest.getSoLuong());
        gioHangItem.setGhiChu(gioHangItem.getGhiChu());
        gioHangItem.setDsTuyChon(gioHangItemRequest.getDsTuyChon());
        return gioHangItem;
    }

    @Override
    public DonHang themDonHang(DonHang donHang) {
        DonHang donHangDaAdd = donHangService.addDonHang(donHang);
        KhachHang khachHang = getKhachHangById(donHang.getMaKhachHang());
        if(donHangDaAdd != null) {
            khachHang.getDsDonHang().add(donHang);
            khachHang.getGioHang().clear();
            updateOne(khachHang);
            return donHangDaAdd;
        }
        return null;
    }

    @Override
    public List<DonHang> getAllDonHang(String maKhachHang) {
        return donHangService.getDonHangByKhachHang(maKhachHang);
    }

    @Override
    public boolean themDiaChi(String maKhachHang, DiaChi diaChi) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            int index = khachHang.getSoDiaChi().indexOf(diaChi);
            if(index < 0) {
                if(khachHang.getSoDiaChi().add(diaChi) && updateOne(khachHang) != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean xoaDiaChi(String maKhachHang, DiaChi diaChi) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            int index = khachHang.getSoDiaChi().indexOf(diaChi);
            if(index >= 0) {
                if(khachHang.getSoDiaChi().remove(diaChi) && updateOne(khachHang) != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean capNhatDiaChi(String maKhachHang, String tenDiaChiCu, DiaChi diaChi) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        if(khachHang != null) {
            int index = khachHang.getSoDiaChi().indexOf(new DiaChi(tenDiaChiCu));
            if(index >= 0) {
                if(khachHang.getSoDiaChi().set(index, diaChi) != null && updateOne(khachHang) != null)
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean themCouponVaoViCoupon(String maKhachHang, String maCoupon) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        Coupon coupon = couponService.getCoupon(maCoupon);
        if(khachHang != null && coupon != null) {
            if(khachHang.getViCoupons().add(new ChiTietViCoupon(coupon, coupon.getSoLuongSuDungToiDa())) && updateOne(khachHang) != null)
                return true;
        }
        return false;
    }

    @Override
    public boolean xoaCouponTrongViCoupon(String maKhachHang, String maCoupon) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        Coupon coupon = couponService.getCoupon(maCoupon);
        if(khachHang != null && coupon != null) {
            if(khachHang.getViCoupons().remove(new ChiTietViCoupon(coupon)) && updateOne(khachHang) != null)
                return true;
        }
        return false;
    }

    @Override
    public String suDungCoupon(String maKhachHang, String maCoupon) {
        KhachHang khachHang = getKhachHangById(maKhachHang);
        Coupon coupon = couponService.getCoupon(maCoupon);
        if(khachHang != null && coupon != null) {
            if(coupon.getSoLuongDaSuDung() < coupon.getTongSoLuong()) {
                ChiTietViCoupon chiTietViCoupon = khachHang.getViCoupons().get(khachHang.getViCoupons().indexOf(new ChiTietViCoupon(coupon)));
                if(chiTietViCoupon.getSoLuongDaSuDung() < coupon.getSoLuongSuDungToiDa()) {
                    LocalDateTime localDateTime = LocalDateTime.now();
                    ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
                    long nowDate = zdt.toInstant().toEpochMilli();
                    if(nowDate >= coupon.getNgayHieuLuc() && nowDate <= coupon.getNgayHetHan()) {
                        chiTietViCoupon.setSoLuongDaSuDung(chiTietViCoupon.getSoLuongDaSuDung() + 1);
                        coupon.setSoLuongDaSuDung(coupon.getSoLuongDaSuDung() + 1);
                        if(updateOne(khachHang) != null &&
                            couponService.updateCoupon(coupon) != null) {
                            return "Sử dụng coupon thành công";
                        } else {
                            return "Cập nhật không thành công";
                        }
                    } else {
                        return "Khách hàng đã sử dụng hết mã giảm giá của mình";
                    }
                }
            } else {
                return "Mã giảm giá đã hết số lượng sử dụng";
            }
        }
        return "Không tìm thấy coupon hoặc khách hàng";
    }
}
