package com.golden.propertymanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.golden.propertymanagement.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
