package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "DonHang")
public class DonHang implements Serializable {

    @Id
    private String maDonHang;
    private long ngayTao;
    private long ngayGiaoHang;
    private String maKhachHang;
    private String hoTenKhachHang;
    private String sdtKhachHang;
    private DonHangStatus trangThai;
    private DiaChi diaChi;
    private String ghiChu;
    private String hinhThucThanhToan;
    private List<ChiTietDonHang> chiTietDonHangs;

    private double tongTien;
    private double giamGia;
    private double phiApDung;
    private double tongThanhTien;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonHang donHang = (DonHang) o;
        return Objects.equals(maDonHang, donHang.maDonHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maDonHang);
    }
}
