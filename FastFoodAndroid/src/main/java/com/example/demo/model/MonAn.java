package com.example.demo.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Document(collection = "MonAn")
public class MonAn implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String maMonAn;

    private String tenMonAn;
    private double gia;
    private String moTa;
    private List<String> dsHinhAnh;
    private boolean trangThai;
    private List<NhomTuyChon> dsNhomTuyChon;
    private List<DanhGia> dsDanhGia;

    @DocumentReference
    private DanhMuc danhMuc;
}
