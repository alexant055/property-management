package com.golden.propertymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.golden.propertymanagement.dto.UserDTO;
import com.golden.propertymanagement.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
		userDTO = userService.register(userDTO);

		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}
}
