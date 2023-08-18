package com.example.demo.service;

import com.example.demo.model.DiaChi;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiaChiService {

    public List<DiaChi> timKiemDiaChi(String text);

    public double getDistance(DiaChi diaChi);

}
