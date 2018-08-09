package com.springboot.rediseEx2.services;

import java.util.List;

import com.springboot.rediseEx2.model.Product;
import com.springboot.rediseEx2.model.ProductForm;

public interface ProductService {
  List<Product> listAll();
  
  Product getByID(String id);
  
  Product save(Product product);
  
  Product update(Product product);
  
  void delete(String id);
  
  Product saveOrUpdateProductForm(ProductForm productForm);
  
}
