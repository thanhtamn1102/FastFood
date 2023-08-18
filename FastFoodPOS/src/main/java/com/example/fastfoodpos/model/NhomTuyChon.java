package com.example.fastfoodpos.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NhomTuyChon {

    private String tenNhomTuyChon;
    private int soLuongToiDa;
    private boolean bacBuoc;
    private List<TuyChon> dsTuyChon;

    public NhomTuyChon() {
        dsTuyChon = new ArrayList<>();
    }
}
