package com.golden.propertymanagement.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.propertymanagement.dto.UserDTO;
import com.golden.propertymanagement.entity.UserEntity;
import com.golden.propertymanagement.repository.UserRepository;
import com.golden.propertymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private ModelMapper mapper;

	@Autowired
	public UserServiceImpl(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public UserDTO register(UserDTO uDTO) {
		// Convert the DTO to entity
		UserEntity uEntity = mapper.map(uDTO, UserEntity.class);
		// Save property
		uEntity = userRepository.save(uEntity);
		// Convert the Entity to DTO
		UserDTO userDTO = mapper.map(uEntity, UserDTO.class);
		// To Skip the password in response
		userDTO.setPassword(null);

		return userDTO;
	}

	@Override
	public UserDTO login(String email, String password) {
		return null;
	}

}
