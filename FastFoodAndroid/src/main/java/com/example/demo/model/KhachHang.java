package com.example.demo.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Document(collection = "KhachHang")
public class KhachHang implements Serializable {

    @Id
    private String maKhachHang;

    private String sdt;

    private String email;

    private String hoTen;

    private List<DiaChi> soDiaChi;

    @DocumentReference
    private List<MonAn> dsMonAnYeuThich;

    private List<GioHangItem> gioHang;

    @DocumentReference
    private List<DonHang> dsDonHang;

    private List<ChiTietViCoupon> viCoupons;


    public KhachHang() {
        dsMonAnYeuThich = new ArrayList<>();
        gioHang = new ArrayList<>();
        dsDonHang = new ArrayList<>();
        viCoupons = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhachHang khachHang = (KhachHang) o;
        return Objects.equals(maKhachHang, khachHang.maKhachHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maKhachHang);
    }
}
