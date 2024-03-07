package com.filarty.zoomarket.models.ElasticModel;


import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "search-product")
@Data
public class ElasticProduct {
    @Id
    private String id;
    private String title;
}
