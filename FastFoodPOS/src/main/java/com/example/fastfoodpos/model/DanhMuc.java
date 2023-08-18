package com.example.fastfoodpos.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DanhMuc implements Serializable, Cloneable {

    @EqualsAndHashCode.Include
    private String maDanhMuc;
    private String tenDanhMuc;
    private boolean trangThai;
    private List<MonAn> dsMonAn;

    public DanhMuc(String tenDanhMuc, boolean trangThai) {
        this.tenDanhMuc = tenDanhMuc;
        this.trangThai = trangThai;
    }

    public DanhMuc(String maDanhMuc, String tenDanhMuc, boolean trangThai) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenDanhMuc;
    }

    @Override
    public DanhMuc clone() throws CloneNotSupportedException {
        DanhMuc danhMuc = (DanhMuc) super.clone();
        if(dsMonAn != null) {
            List<MonAn> dsMonAn = (List<MonAn>) ((ArrayList<MonAn>)this.dsMonAn).clone();
            danhMuc.setDsMonAn(dsMonAn);
        }
        return danhMuc;
    }
}
