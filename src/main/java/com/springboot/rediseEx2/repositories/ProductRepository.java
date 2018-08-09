package com.springboot.rediseEx2.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.rediseEx2.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
