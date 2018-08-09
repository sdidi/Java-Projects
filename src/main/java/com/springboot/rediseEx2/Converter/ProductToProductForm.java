package com.springboot.rediseEx2.Converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.springboot.rediseEx2.model.Product;
import com.springboot.rediseEx2.model.ProductForm;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {

	@Override
	public ProductForm convert(Product product) {
		ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setName(product.getName());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
       
        return productForm;
	}
	

}
