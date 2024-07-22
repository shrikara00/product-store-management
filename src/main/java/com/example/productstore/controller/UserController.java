package com.example.productstore.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.productstore.domain.Product;
import com.example.productstore.domain.Purchases;
import com.example.productstore.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	public Purchases purchasedproduct= new Purchases();

	@GetMapping("/userhome")
	public String userHome(Model model) {
		model.addAttribute("products", userService.getAllProducts());
		return "userhome";
	}

	@GetMapping("/adminhome/purchase/{id}")
	public String purchasePage(@PathVariable Long id, Model model) {
		Optional<Product> prod=userService.getProductById(id);
		Product product=prod.get();
		purchasedproduct.setProductid(product.getId());
		purchasedproduct.setCategory(product.getCategory());
		purchasedproduct.setProductName(product.getProductName());
		purchasedproduct.setPrice(product.getPrice());
		model.addAttribute("productDetail", purchasedproduct);
		return "purchase";
	}
	    
	@PostMapping("/adminhome/purchase/buynow")
	public String buyNow(@ModelAttribute("productDetail") Purchases productDetail ){
		userService.addPurchaseDetails(productDetail);
		return "redirect:/userhome";
	}
	
}
