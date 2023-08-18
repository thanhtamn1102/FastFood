package com.example.demo.repository;

import com.example.demo.model.MonAn;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonAnRepository extends MongoRepository<MonAn, String> {
}
