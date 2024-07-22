package com.example.productstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productstore.domain.Product;
import com.example.productstore.repository.ProductRepository;

@Service
public class AdminService {
	
	@Autowired
	ProductRepository productRepository;

	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		List<Product> products= productRepository.findAll();
		return products;
	}
	
	public Optional<Product> getProductById(Long id) {
		Optional<Product> product= productRepository.findById(id);
		return product;
	}
	
	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }

}
