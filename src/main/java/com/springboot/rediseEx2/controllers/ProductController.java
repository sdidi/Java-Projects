package com.springboot.rediseEx2.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.rediseEx2.Converter.ProductToProductForm;
import com.springboot.rediseEx2.model.Product;
import com.springboot.rediseEx2.model.ProductForm;
import com.springboot.rediseEx2.services.ProductService;



//@EntityScan( basePackages = {"com.springboot.rediseEx2"} )
//@RestController
//@RequestMapping("/api")
@Controller
public class ProductController {
	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;
	private ProductToProductForm productToProductForm;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setProductToProductForm(ProductToProductForm productToProductForm) {
		this.productToProductForm = productToProductForm;
	}
	
    @RequestMapping(value = "/products/", method = RequestMethod.GET)
	    public ResponseEntity<List<Product>> listAllProducts() {
	        List<Product> products = productService.listAll();
	        if (products.isEmpty()) {
	        	logger.error("No Products found.");
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            }
	        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	    }
	
    //public String redirToList() {
	@RequestMapping("/")
	public String index() {
		return "/product/list";
	}
	
	@RequestMapping({"/product/list","/product"})
	public String listProducts(Model model)
	{
		model.addAttribute("product", productService.listAll());
		return "/product/list";
	}
	
	
	@RequestMapping({"/product/show/{id}"})
	public String getProduct(@PathVariable String id, Model model)
	{
		model.addAttribute("product", productService.getByID(id));
		return "/product/show";
	}
	
	
	@RequestMapping({"/product/edit/{id}"})
	public String edit(@PathVariable String id, Model model)
	{
		Product product = productService.getByID(id);
		ProductForm productForm = productToProductForm.convert(product);
		
		
		model.addAttribute("productForm", productForm);
		return "/product/productForm";
	}
	
	@RequestMapping("/product/new")
    public String newProduct(Model model){
		logger.error("It calls this new product method.");
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "product/productform";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }
	
	 @RequestMapping(value = "/product/", method = RequestMethod.POST)
	    public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating product heree : {}", product);
	 
	        productService.save(product)  ;    
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	 

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id){
        productService.delete(id);
        return "redirect:/product/list";
    }
	
	
	
	
}
