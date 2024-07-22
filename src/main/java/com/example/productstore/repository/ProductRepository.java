package com.example.productstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productstore.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
