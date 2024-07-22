package com.example.productstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productstore.domain.Purchases;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchases,Long>{
	List<Purchases> findByEmail(String email);
}
