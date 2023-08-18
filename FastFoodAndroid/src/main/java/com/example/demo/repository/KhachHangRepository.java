package com.example.demo.repository;

import com.example.demo.model.KhachHang;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends MongoRepository<KhachHang, String> {

    @Query("{'sdt': ?0}")
    public KhachHang findBySdt(String sdt);

}
