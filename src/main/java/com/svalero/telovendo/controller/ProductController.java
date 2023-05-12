package com.svalero.telovendo.controller;

import com.svalero.telovendo.domain.Product;
import com.svalero.telovendo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // Lista todos los productos
    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> product;
        product = productService.findAllProducts();
        return product;
    }


}
