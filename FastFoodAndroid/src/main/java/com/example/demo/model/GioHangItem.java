package com.example.demo.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GioHangItem implements Serializable {

    @DocumentReference
    private MonAn monAn;

    private int soLuong;
    private String ghiChu;
    private List<TuyChonWithSoLuong> dsTuyChon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GioHangItem that = (GioHangItem) o;

        boolean compareDsTuyChon = true;
        for(TuyChonWithSoLuong tuyChon : dsTuyChon) {
            if(!that.dsTuyChon.contains(tuyChon)) {
                compareDsTuyChon = false;
                break;
            }
        }

        return Objects.equals(monAn, that.monAn) && compareDsTuyChon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monAn, dsTuyChon);
    }
}
