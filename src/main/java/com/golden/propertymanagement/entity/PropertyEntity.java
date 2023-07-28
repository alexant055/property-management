package com.golden.propertymanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROPERTY")
@Data
@NoArgsConstructor
public class PropertyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "ownerName", nullable = false)
	private String ownerName;
	@Column(name = "ownerEmail", nullable = false)
	private String ownerEmail;
	@Column(name = "price")
	private Double price;
	@Column(name = "address")
	private String address;
}
