package com.svalero.telovendo.repository;

import com.svalero.telovendo.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();
}
