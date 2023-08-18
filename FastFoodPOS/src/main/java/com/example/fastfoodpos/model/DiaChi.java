package com.example.fastfoodpos.model;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaChi {

    private String tenDiaChi;
    private String diaChi;
    private String ghiChu;
    private List<Double> position;

    public DiaChi(String tenDiaChi) {
        this.tenDiaChi = tenDiaChi;
    }

    @Override
    public String toString() {
        String diaChi = "";
        if(ghiChu != null && !ghiChu.trim().isEmpty())
            diaChi += ghiChu + ", ";
        diaChi += this.getDiaChi();
        return diaChi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaChi diaChi = (DiaChi) o;
        return Objects.equals(tenDiaChi, diaChi.tenDiaChi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenDiaChi);
    }
}
