package com.example.productstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.productstore.domain.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,String>{
	Registration findByEmailAndPassword(String email,String password);
}
