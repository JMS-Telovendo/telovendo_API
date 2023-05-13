package com.svalero.telovendo.service;

import com.svalero.telovendo.domain.Product;
import com.svalero.telovendo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product deleteProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(null);
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product modifyProduct(Product newProduct, long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(null);
        product.setProduct_name(newProduct.getProduct_name());
        product.setCategory(newProduct.getCategory());
        product.setPrice(newProduct.getPrice());
        product.setSellingDate(newProduct.getSellingDate());
        //TODO Available es un bolean(ver como ponerlo)
        product.setLatitude(newProduct.getLatitude());
        product.setLongitude(newProduct.getLongitude());
        product.setUser(newProduct.getUser());

        return productRepository.save(product);
    }

    @Override
    public Product findProduct(long id) {
        return productRepository.findById(id)
                .orElseThrow(null);
    }
}
