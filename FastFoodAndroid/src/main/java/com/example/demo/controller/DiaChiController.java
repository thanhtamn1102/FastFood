package com.example.demo.controller;

import com.example.demo.model.DiaChi;
import com.example.demo.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/fastfood/api/diachi")
public class DiaChiController {

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "tendiachi") String tenDiaChi) {
        return ResponseEntity.ok(diaChiService.timKiemDiaChi(tenDiaChi));
    }

    @PostMapping("/distance")
    public ResponseEntity<?> getDistance(@RequestBody DiaChi diaChi) {
        return ResponseEntity.ok(diaChiService.getDistance(diaChi));
    }

}
