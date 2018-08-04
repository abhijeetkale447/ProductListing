package com.rakuten.productListings.ProductListings.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakuten.productListings.ProductListings.model.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUserName(String name);
}
