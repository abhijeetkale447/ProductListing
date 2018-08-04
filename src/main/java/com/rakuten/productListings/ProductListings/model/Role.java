package com.rakuten.productListings.ProductListings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "role_id_gen")
	@SequenceGenerator(name = "role_id_gen", sequenceName = "ROLE_ID_SEQUENCE", allocationSize = 1)	
	@Column(name="role_id")
	private int id;
	
	@Column(name="role")
	private String role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
