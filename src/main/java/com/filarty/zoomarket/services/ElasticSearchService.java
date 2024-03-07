package com.filarty.zoomarket.services;


import com.filarty.zoomarket.models.ElasticModel.ElasticProduct;
import com.filarty.zoomarket.repository.ElasticSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchService{
    private final ElasticSearchRepository elasticSearchRepository;
    public List<Long> findProductsByTitle(String name) {
        Streamable<ElasticProduct> product = elasticSearchRepository.findByTitleContaining(name);
        List<Long> products_id = new ArrayList<>();
        product.forEach((obj) -> {
            products_id.add(Long.valueOf(obj.getId()));
        });
        log.info("info: {}", products_id);
        return products_id;
    }
    public void saveProduct(ElasticProduct elasticProduct) {
        elasticSearchRepository.save(elasticProduct);
    }

}
