package com.filarty.zoomarket.services;


import com.filarty.zoomarket.models.ElasticModel.ElasticProduct;
import com.filarty.zoomarket.models.Image;
import com.filarty.zoomarket.models.Product;
import com.filarty.zoomarket.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IproductService{
    private final ProductRepository productRepository;
    private final ElasticSearchService elasticSearchService;
    @Override
    @Cacheable(value = "product", key = "'allProducts'")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @CacheEvict(value = "product", allEntries = true)
    public Product createProduct(Product product) {
        Product productData = productRepository.save(product);
        ElasticProduct elasticProduct = new ElasticProduct();
        elasticProduct.setId(productData.getId().toString());
        elasticProduct.setTitle(product.getName());
        elasticSearchService.saveProduct(elasticProduct);
        return productData;
    }

    @Override
    @SneakyThrows
    @Cacheable(value = "product", key = "#id")
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<Product> findProductsByName(String name) {
        List<Long> products_id = elasticSearchService.findProductsByTitle(name);
        return productRepository.findAllById(products_id);
    }
}
