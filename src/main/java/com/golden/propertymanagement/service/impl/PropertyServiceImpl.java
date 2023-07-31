package com.golden.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.golden.propertymanagement.dto.PropertyDTO;
import com.golden.propertymanagement.entity.PropertyEntity;
import com.golden.propertymanagement.repository.PropertyRepository;
import com.golden.propertymanagement.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public PropertyDTO saveProperty(PropertyDTO pDto) {
		// Convert the DTO to entity
		PropertyEntity pEntity = mapper.map(pDto, PropertyEntity.class);
		// Save property
		pEntity = propertyRepository.save(pEntity);
		// Convert the Entity to DTO
		return mapper.map(pEntity, PropertyDTO.class);
	}

	@Override
	public List<PropertyDTO> getAll() {
		// Get list of properties from DB
		List<PropertyEntity> entities = (List<PropertyEntity>) propertyRepository.findAll();
		// Convert entity list to DTO list
		List<PropertyDTO> pList = new ArrayList<>();
		for (PropertyEntity propertyEntity : entities) {
			pList.add(mapper.map(propertyEntity, PropertyDTO.class));
		}

		return pList;
	}

	@Override
	public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
		try {
			Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
			if (propertyEntity.isPresent()) {
				// Convert the DTO to entity
				// Override DB values with request values
				PropertyEntity pEntity = mapper.map(propertyDTO, PropertyEntity.class);
				pEntity.setId(propertyId);
				// Save property
				pEntity = propertyRepository.save(pEntity);
				// Convert the Entity to DTO
				propertyDTO = mapper.map(pEntity, PropertyDTO.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyDTO;
	}

	@Override
	public void deleteProperty(Long propertyId) {
		propertyRepository.deleteById(propertyId);
	}

}
