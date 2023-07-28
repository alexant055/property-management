package com.golden.propertymanagement.service;

import java.util.List;

import com.golden.propertymanagement.dto.PropertyDTO;

public interface PropertyService {
	PropertyDTO saveProperty(PropertyDTO propertyDTO);
	List<PropertyDTO> getAll();
	PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);
	void deleteProperty(Long propertyId);
}
