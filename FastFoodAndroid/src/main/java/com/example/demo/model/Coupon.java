package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "Coupon")
public class Coupon implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String maCoupon;

    private double giaTriGiam;
    private int donViGiam;
    private int donViGiamToiDa;
    private double giaTriGiamToiDa;
    private double giaTriDonHangToiThieu;
    private String moTa;
    private long ngayTao;
    private long ngayHieuLuc;
    private long ngayHetHan;
    private int tongSoLuong;
    private int soLuongDaSuDung;
    private int soLuongSuDungToiDa;

}
