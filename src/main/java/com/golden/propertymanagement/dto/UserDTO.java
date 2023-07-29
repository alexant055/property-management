package com.golden.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
	private Long id;
	private String ownerName;
	@NotNull(message = "Owner email is mandatory.")
	@NotEmpty(message = "Owner email can not be empty.")
	@Size(min = 1, max = 50, message = "Owner email should be 1 to 50 characters in length.")
	private String ownerEmail;
	private String phone;
	@NotNull(message = "Password is mandatory.")
	@NotEmpty(message = "Password can not be empty.")
	private String password;
}
