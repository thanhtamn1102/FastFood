package com.example.demo.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChiTietDonHang implements Serializable {

    @DocumentReference
    private MonAn monAn;
    private int soLuong;
    private String ghiChu;
    private List<TuyChonWithSoLuong> dsTuyChon;

}
