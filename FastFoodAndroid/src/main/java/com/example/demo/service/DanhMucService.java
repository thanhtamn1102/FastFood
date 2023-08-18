package com.example.demo.service;

import com.example.demo.model.DanhMuc;
import com.example.demo.model.MonAn;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DanhMucService {

    public List<DanhMuc> getAllDanhMuc();
    public DanhMuc addDanhMuc(DanhMuc danhMuc);
    public DanhMuc updateDanhMuc(DanhMuc danhMuc);
    public boolean deleteDanhMuc(String maDanhMuc);

}
