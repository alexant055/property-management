package com.golden.propertymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {
	private Long id;
	private String title;
	private String description;
	private String ownerName;
	private String ownerEmail;
	private Double price;
	private String address;
}
