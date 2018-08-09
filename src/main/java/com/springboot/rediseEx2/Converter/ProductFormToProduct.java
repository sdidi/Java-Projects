package com.springboot.rediseEx2.Converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.springboot.rediseEx2.model.Product;
import com.springboot.rediseEx2.model.ProductForm;
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product>{

	@Override
	public Product convert(ProductForm productForm) {
		 Product product = new Product();
	        if (productForm.getId() != null  && !StringUtils.isEmpty(productForm.getId())) {
	            product.setId(productForm.getId());
	        }
	        product.setName(productForm.getName());
	        product.setDescription(productForm.getDescription());
	        product.setPrice(productForm.getPrice());
	       return product;
	}
	
	

}
