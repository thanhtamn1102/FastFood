package com.example.demo.service_impl;

import com.example.demo.model.MonAn;
import com.example.demo.repository.MonAnRepository;
import com.example.demo.service.MonAnService;
import com.example.demo.utlis.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonAnServiceImpl implements MonAnService {

    @Autowired
    private MonAnRepository monAnRepository;

    @Override
    public List<MonAn> findAll() {
        return monAnRepository.findAll();
    }

    @Override
    public MonAn findById(String id) {
        return monAnRepository.findById(id).orElse(null);
    }

    @Override
    public List<MonAn> search(String tenMonAn) {
        List<MonAn> dsMonAn = findAll();
        List<MonAn> result = new ArrayList<>();
        String tenMonAnCanTimKhongDau = StringUtils.removeAccent(tenMonAn).toLowerCase().trim();
        dsMonAn.forEach(monAn -> {
            String tenMonAnKhongDau = StringUtils.removeAccent(monAn.getTenMonAn()).toLowerCase().trim();
            if(tenMonAnKhongDau.contains(tenMonAnCanTimKhongDau)) {
                result.add(monAn);
            }
        });
        return result;
    }

    @Override
    public MonAn insertOne(MonAn monAn) {
        return monAnRepository.save(monAn);
    }

    @Override
    public MonAn updateOne(MonAn monAn) {
        return monAnRepository.save(monAn);
    }

    @Override
    public void updateMany(List<MonAn> dsMonAn) {
        for(MonAn monAn : dsMonAn) {
            updateOne(monAn);
        }
    }

    @Override
    public boolean deleteMonAn(String maMonAn) {
        try {
            monAnRepository.deleteById(maMonAn);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
