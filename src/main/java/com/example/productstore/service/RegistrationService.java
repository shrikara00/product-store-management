package com.example.productstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productstore.domain.Registration;
import com.example.productstore.repository.RegistrationRepository;



@Service
public class RegistrationService {
	@Autowired 
	private RegistrationRepository registerRepository;
	
	public void addDetails(Registration user) {
		registerRepository.save(user);
	}
	
	public boolean findUser(String email,String password) {
		Registration loggingUser= registerRepository.findByEmailAndPassword(email, password);
		if(loggingUser!=null)
				return true;
		else
			return false;
	}
}
