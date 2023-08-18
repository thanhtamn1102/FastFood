package com.example.fastfoodpos.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DanhGia {

    private String danhGia;
    private int diem;
    private String tenKhachHang;
    private float thoiGian;
    private List<String> dsHinhAnh;

}
