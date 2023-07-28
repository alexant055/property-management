package com.golden.propertymanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.golden.propertymanagement.entity.PropertyEntity;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
}
