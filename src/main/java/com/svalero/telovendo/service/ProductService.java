package com.svalero.telovendo.service;

import com.svalero.telovendo.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product deleteProduct(long id);

    Product addProduct(Product product);

    Product modifyProduct(Product product, long id);

    Product findProduct(long id);
}
