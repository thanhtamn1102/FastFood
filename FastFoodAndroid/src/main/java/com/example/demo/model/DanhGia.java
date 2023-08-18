package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DanhGia implements Serializable {

    private String danhGia;
    private int diem;
    private String tenKhachHang;
    private float thoiGian;
    private List<String> dsHinhAnh;

}
