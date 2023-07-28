package com.golden.propertymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.golden.propertymanagement.dto.PropertyDTO;
import com.golden.propertymanagement.service.PropertyService;

@RestController
@RequestMapping(value = "/api/v1/properties")
public class PropertyController {

	@Value("${pms.description}")
	String pmsDescription;

	@Autowired
	private PropertyService propertyService;

	@PostMapping
	public ResponseEntity<PropertyDTO> save(@RequestBody PropertyDTO propertyDTO) {
		propertyDTO = propertyService.saveProperty(propertyDTO);
		
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
		
		return responseEntity;
	}

	@GetMapping
	public ResponseEntity<List<PropertyDTO>> getAllProperties() {
		System.out.println(pmsDescription);
		List<PropertyDTO> pList = propertyService.getAll();
		return new ResponseEntity<>(pList, HttpStatus.OK);
	}
	
	@PutMapping("/{propertyId}")
	public ResponseEntity<PropertyDTO> updateProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId) {
		propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
		
		ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
		
		return responseEntity;
	}
	
	@DeleteMapping("/{propertyId}")
	public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId) {
		propertyService.deleteProperty(propertyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
