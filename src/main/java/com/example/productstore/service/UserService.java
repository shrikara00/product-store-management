package com.example.productstore.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productstore.domain.Product;
import com.example.productstore.domain.Purchases;
import com.example.productstore.repository.ProductRepository;
import com.example.productstore.repository.PurchaseRepository;

@Service
public class UserService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;;
	
	public List<Product> getAllProducts(){
		List<Product> products= productRepository.findAll();
		return products;
	}
	
	public Optional<Product> getProductById(Long id) {
		Optional<Product> product= productRepository.findById(id);
		return product;
	}
	
	public void addPurchaseDetails(Purchases purchased) {
		purchaseRepository.save(purchased);
	}
	
	public List<Purchases> findMyPurchases(String email){
	    List<Purchases> myPurchases = purchaseRepository.findByEmail(email);
	    if (myPurchases == null) {
	        return Collections.emptyList(); // Return an empty list if no purchases are found
	    } else {
	        return myPurchases;
	    }
	}

}
