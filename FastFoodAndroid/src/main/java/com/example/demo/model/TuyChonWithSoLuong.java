package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TuyChonWithSoLuong implements Serializable {

    private TuyChon tuyChon;
    private int soLuong;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuyChonWithSoLuong that = (TuyChonWithSoLuong) o;
        return Objects.equals(tuyChon, that.tuyChon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tuyChon);
    }
}
