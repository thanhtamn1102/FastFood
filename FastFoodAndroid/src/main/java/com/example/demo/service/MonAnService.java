package com.example.demo.service;

import com.example.demo.model.MonAn;
import com.example.demo.repository.MonAnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MonAnService {

    public List<MonAn> findAll();
    public MonAn findById(String id);
    public List<MonAn> search(String tenMonAn);
    public MonAn insertOne(MonAn monAn);
    public MonAn updateOne(MonAn monAn);
    public void updateMany(List<MonAn> dsMonAn);
    public boolean deleteMonAn(String maMonAn);

}
