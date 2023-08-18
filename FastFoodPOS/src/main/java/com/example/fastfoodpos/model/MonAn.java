package com.example.fastfoodpos.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class MonAn implements Cloneable {

    @EqualsAndHashCode.Include
    private String maMonAn;

    private String tenMonAn;
    private double gia;
    private String moTa;
    private List<String> dsHinhAnh;
    private boolean trangThai;
    private List<NhomTuyChon> dsNhomTuyChon;
    private List<DanhGia> dsDanhGia;
    private DanhMuc danhMuc;

    @Override
    public MonAn clone() throws CloneNotSupportedException {
        MonAn monAn = (MonAn) super.clone();

        if(this.dsNhomTuyChon != null) {
            List<NhomTuyChon> dsNhomTuyChon = (List<NhomTuyChon>) ((ArrayList<NhomTuyChon>)this.dsNhomTuyChon).clone();
            monAn.setDsNhomTuyChon(dsNhomTuyChon);
        }

        if(this.dsDanhGia != null) {
            List<DanhGia> dsDanhGia = (List<DanhGia>) ((ArrayList<DanhGia>)this.dsDanhGia).clone();
            monAn.setDsDanhGia(dsDanhGia);
        }

        if(this.danhMuc != null) {
            DanhMuc danhMuc = this.danhMuc.clone();
            monAn.setDanhMuc(danhMuc);
        }

        return monAn;
    }
}
