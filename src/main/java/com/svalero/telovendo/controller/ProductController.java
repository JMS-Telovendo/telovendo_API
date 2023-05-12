package com.svalero.telovendo.controller;

import com.svalero.telovendo.domain.Product;
import com.svalero.telovendo.exception.ErrorMessage;
import com.svalero.telovendo.exception.ProductNotFoundException;
import com.svalero.telovendo.exception.UserNotFoundException;
import com.svalero.telovendo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
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

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFoundException(UserNotFoundException pnfe){
        logger.error(pnfe.getMessage(),pnfe);
        ErrorMessage notfound = new ErrorMessage(404,pnfe.getMessage());
        return new ResponseEntity(notfound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> badRequestException(MethodArgumentNotValidException manve){

        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldname = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldname, message);
        });

        logger.error(manve.getMessage(),manve);
        ErrorMessage badRequest = new ErrorMessage(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        logger.error(e.getMessage(),e);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
