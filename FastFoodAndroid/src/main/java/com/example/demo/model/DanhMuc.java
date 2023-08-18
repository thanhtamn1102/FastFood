package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "DanhMuc")
public class DanhMuc implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String maDanhMuc;
    private String tenDanhMuc;
    private boolean trangThai;

    @DocumentReference(lazy = true)
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
}
