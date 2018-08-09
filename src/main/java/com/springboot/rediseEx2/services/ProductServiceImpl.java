package com.springboot.rediseEx2.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rediseEx2.Converter.ProductFormToProduct;
import com.springboot.rediseEx2.model.Product;
import com.springboot.rediseEx2.model.ProductForm;
import com.springboot.rediseEx2.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	private ProductFormToProduct productFormToProduct;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductFormToProduct productFormToProduct) {
        this.productRepository = productRepository;
        this.productFormToProduct = productFormToProduct;
    }

	@Override
	public List<Product> listAll() {
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	@Override
	public Product getByID(String id) {		
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product save(Product product) {
		productRepository.save(product);
		return product;
	}

	@Override
	public Product update(Product product) {
		save(product);
		return product;
	}

	@Override
	public void delete(String id) {
		productRepository.deleteById(id);
		
	}
	
	 @Override
	    public Product saveOrUpdateProductForm(ProductForm productForm) {
	        Product savedProduct = save(productFormToProduct.convert(productForm));

	        System.out.println("Saved Product Id: " + savedProduct.getId());
	        return savedProduct;
	    }
    
}
