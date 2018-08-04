package com.rakuten.productListings.ProductListings.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.swing.plaf.basic.BasicTreeUI.TreeHomeAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rakuten.productListings.ProductListings.model.Role;
import com.rakuten.productListings.ProductListings.model.User;
import com.rakuten.productListings.ProductListings.model.request.UserRequest;
import com.rakuten.productListings.ProductListings.repository.RoleRepository;
import com.rakuten.productListings.ProductListings.repository.UserRepository;

@Service
public class UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public void saveUser(UserRequest userRequest) throws Exception {
		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(userRequest.getRole());
        if(userRole == null) {
        	throw new Exception("Bad Request: Role doesn't exists.");
        }
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

}
