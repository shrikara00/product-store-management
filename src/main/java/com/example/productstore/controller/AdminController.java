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
import com.example.productstore.service.AdminService;

@Controller
public class AdminController {
		
		@Autowired
		AdminService adminService;
		
		@GetMapping("/adminhome")
		public String adminHome(Model model) {
			model.addAttribute("products", adminService.getAllProducts());
			return "adminhome";
		}
		
		@GetMapping("/adminhome/addnew")
		public String addNewProduct(Model model) {
			model.addAttribute("product", new Product());
			return "addnewproduct";
		}
		
		@PostMapping("/adminhome/addnew/add")
		public String addingNewProduct(@ModelAttribute("product") Product product) {
			adminService.addProduct(product);
			return "redirect:/adminhome";
		}
		
		@GetMapping("/adminhome/edit/{id}")
		public String editProduct(@PathVariable Long id,Model model) {
			Optional<Product> products= adminService.getProductById(id);
			model.addAttribute("product", products.get());
			return "addnewproduct";
		}
		
		@GetMapping("/adminhome/delete/{id}")
		public String deleteProduct(@PathVariable Long id) {
			adminService.deleteProduct(id);
			return "redirect:/adminhome";
		}
}
