package com.example.fastfoodpos.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TuyChon {

    private String tenTuyChon;
    private double gia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuyChon tuyChon = (TuyChon) o;
        return gia == tuyChon.gia && Objects.equals(tenTuyChon, tuyChon.tenTuyChon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenTuyChon, gia);
    }
}
