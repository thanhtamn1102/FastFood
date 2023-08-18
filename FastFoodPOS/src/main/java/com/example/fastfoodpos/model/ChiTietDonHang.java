package com.example.fastfoodpos.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChiTietDonHang {

    private MonAn monAn;
    private int soLuong;
    private String ghiChu;
    private List<TuyChonWithSoLuong> dsTuyChon;

}
