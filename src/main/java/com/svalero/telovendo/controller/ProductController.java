package com.svalero.telovendo.controller;

import com.svalero.telovendo.domain.Product;
import com.svalero.telovendo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // Busca un producto por ID
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable long id) {
        Product product = productService.findProduct(id);

        return product;
    }

    // Borra un producto por id
    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable long id) {

        Product product = productService.deleteProduct(id);

        return product;
    }

    // Registra un nuevo producto
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);

        return newProduct;
    }

    // Modifica un producto por id
    @PutMapping("/product/{id}")
    public Product modifyProduct(@RequestBody Product product, @PathVariable long id) {
        Product newProduct = productService.modifyProduct(product, id);

        return newProduct;
    }


}
