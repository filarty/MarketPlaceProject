package com.filarty.zoomarket.repository;

import com.filarty.zoomarket.models.ElasticModel.ElasticProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<ElasticProduct, String> {
    Streamable<ElasticProduct> findByTitleContaining(String title);
}
