package com.example.demo.controller;

import com.example.demo.model.MonAn;
import com.example.demo.service.MonAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fastfood/api/monan")
public class MonAnController {

    @Autowired
    private MonAnService monAnService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(monAnService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(monAnService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> insertOne(@RequestBody MonAn monAn) {
        return ResponseEntity.ok(monAnService.insertOne(monAn));
    }

    @PutMapping("")
    public ResponseEntity<?> updateOne(@RequestBody MonAn monAn) {
        System.out.println(monAn);
        return ResponseEntity.ok(monAnService.updateOne(monAn));
    }

    @PutMapping("/updatemany")
    public void updateMany(@RequestBody List<MonAn> dsMonAn) {
        System.out.println(dsMonAn);
        monAnService.updateMany(dsMonAn);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "tenmonan") String tenMonAn) {
        return ResponseEntity.ok(monAnService.search(tenMonAn));
    }

    @DeleteMapping("/{maMonAn}")
    public ResponseEntity<?> deleteMonAn(@PathVariable("maMonAn") String maMonAn) {
        return ResponseEntity.ok(monAnService.deleteMonAn(maMonAn));
    }

}
