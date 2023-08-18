package com.example.demo.service_impl;

import com.example.demo.model.DanhMuc;
import com.example.demo.repository.DanhMucRepository;
import com.example.demo.service.DanhMucService;
import com.example.demo.service.MonAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DanhMucServiceImpl implements DanhMucService {

    @Autowired
    private DanhMucRepository danhMucRepository;

    @Autowired
    private MonAnService monAnService;

    @Override
    public List<DanhMuc> getAllDanhMuc() {
        return danhMucRepository.findAll();
    }

    @Override
    public DanhMuc addDanhMuc(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public DanhMuc updateDanhMuc(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }

    @Override
    public boolean deleteDanhMuc(String maDanhMuc) {
        try {
            danhMucRepository.deleteById(maDanhMuc);
            return true;
        } catch (Exception ex) { }
        return false;
    }
}
