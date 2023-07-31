package com.golden.propertymanagement.service;

import com.golden.propertymanagement.dto.UserDTO;

public interface UserService {
	UserDTO register(UserDTO userDTO);

	UserDTO login(String email, String password);
}
