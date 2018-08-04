package com.rakuten.productListings.ProductListings.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rakuten.productListings.ProductListings.model.User;
import com.rakuten.productListings.ProductListings.model.request.UserRequest;
import com.rakuten.productListings.ProductListings.model.response.Response;
import com.rakuten.productListings.ProductListings.model.response.ResponseFactory;
import com.rakuten.productListings.ProductListings.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Response> createNewUser(@RequestBody  UserRequest user) {
		User userExists = userService.findUserByUserName(user.getUserName());
		if (userExists != null) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(
					"There is already a user registered with the username provided"), HttpStatus.BAD_REQUEST);
		}
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			 return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(), HttpStatus.CREATED);
	}

}
