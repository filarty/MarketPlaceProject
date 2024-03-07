package com.filarty.zoomarket.repository;

import com.filarty.zoomarket.models.Product;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
    List<Product> findProductsById(Long id);
}
