package com.filarty.zoomarket.services;

import com.filarty.zoomarket.models.Product;

import java.util.List;
import java.util.Optional;

public interface IproductService {
    List<Product> getProducts();
    Product createProduct(Product product);
    Optional<Product> findProductById(Long id);
    Product updateProduct(Product product);
    void deleteProduct(Long id);

}
