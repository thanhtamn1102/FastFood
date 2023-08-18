package com.example.demo.controller;

import com.example.demo.model.DanhMuc;
import com.example.demo.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fastfood/api/danhmuc")
public class DanhMucController {

    @Autowired
    private DanhMucService danhMucService;

    @GetMapping("")
    public ResponseEntity<?> getAllDanhMuc() {
        return ResponseEntity.ok(danhMucService.getAllDanhMuc());
    }

    @PostMapping("")
    public ResponseEntity<?> addDanhMuc(@RequestBody DanhMuc danhMuc) {
        return ResponseEntity.ok(danhMucService.addDanhMuc(danhMuc));
    }

    @PutMapping("")
    public ResponseEntity<?> updateDanhMuc(@RequestBody DanhMuc danhMuc) {
        return ResponseEntity.ok(danhMucService.updateDanhMuc(danhMuc));
    }

    @DeleteMapping("/{maDanhMuc}")
    public ResponseEntity<?> deleteDanhMuc(@PathVariable("maDanhMuc") String maDanhMuc) {
        return ResponseEntity.ok(danhMucService.deleteDanhMuc(maDanhMuc));
    }
}
