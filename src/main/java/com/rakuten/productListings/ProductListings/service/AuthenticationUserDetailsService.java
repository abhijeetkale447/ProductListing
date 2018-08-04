package com.rakuten.productListings.ProductListings.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rakuten.productListings.ProductListings.model.User;
import com.rakuten.productListings.ProductListings.model.AuthUserDetails;
import com.rakuten.productListings.ProductListings.repository.UserRepository;

@Service
public class AuthenticationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUserName(username);
        return new AuthUserDetails(user);
    }
}