package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NhomTuyChon implements Serializable {

    private String tenNhomTuyChon;
    private int soLuongToiDa;
    private boolean bacBuoc;
    private List<TuyChon> dsTuyChon;

}
