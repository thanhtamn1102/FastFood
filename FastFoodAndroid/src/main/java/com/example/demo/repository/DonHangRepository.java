package com.example.demo.repository;

import com.example.demo.model.DonHang;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonHangRepository extends MongoRepository<DonHang, String> {

}
