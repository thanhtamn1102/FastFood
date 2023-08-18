package com.example.demo.repository;

import com.example.demo.model.DanhMuc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucRepository extends MongoRepository<DanhMuc, String> {
}
