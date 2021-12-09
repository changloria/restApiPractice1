package com.example.restapi.repository;

import com.example.restapi.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepositoryNew extends MongoRepository<Company, String> {
}
