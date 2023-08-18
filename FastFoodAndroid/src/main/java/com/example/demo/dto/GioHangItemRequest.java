package com.example.demo.dto;

import com.example.demo.model.MonAn;
import com.example.demo.model.TuyChonWithSoLuong;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GioHangItemRequest {

    private String maMonAn;
    private int soLuong;
    private String ghiChu;
    private List<TuyChonWithSoLuong> dsTuyChon;

}
