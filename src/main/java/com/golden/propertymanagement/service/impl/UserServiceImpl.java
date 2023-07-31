package com.golden.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.propertymanagement.dto.UserDTO;
import com.golden.propertymanagement.entity.UserEntity;
import com.golden.propertymanagement.exception.BusinessException;
import com.golden.propertymanagement.exception.ErrorModel;
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
		// To Skip the password in response
		mapper.typeMap(UserEntity.class, UserDTO.class).addMappings(map -> map.skip(UserDTO::setPassword));
	}

	@Override
	public UserDTO register(UserDTO uDTO) {

		Optional<UserEntity> optionalEntity = userRepository.findByOwnerEmail(uDTO.getOwnerEmail());

		if (optionalEntity.isEmpty()) {
			// Convert the DTO to entity
			UserEntity uEntity = mapper.map(uDTO, UserEntity.class);
			// Save property
			uEntity = userRepository.save(uEntity);
			// Convert the Entity to DTO
			return mapper.map(uEntity, UserDTO.class);
		} else {
			List<ErrorModel> errorModels = new ArrayList<>();
			errorModels.add(new ErrorModel("EMAIL_EXIST", "Email which you are trying to register is already exist."));
			throw new BusinessException(errorModels);
		}
	}

	@Override
	public UserDTO login(String email, String password) {
		Optional<UserEntity> oEntity = userRepository.findByOwnerEmailAndPassword(email, password);
		UserDTO userDTO = null;

		if (oEntity.isPresent()) {
			// Convert the Entity to DTO
			userDTO = mapper.map(oEntity.get(), UserDTO.class);
		} else {
			List<ErrorModel> errorModels = new ArrayList<>();
			errorModels.add(new ErrorModel("INVALID_LOGIN", "Incorrect username or password"));
			throw new BusinessException(errorModels);
		}

		return userDTO;
	}

}
