package com.filarty.zoomarket.config;

import com.filarty.zoomarket.prop.ElasticSearchProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
@Configuration
@RequiredArgsConstructor
public class ElasticSearchClientConfiguration extends ElasticsearchConfiguration {
    private final ElasticSearchProperties elasticSearchProperties;
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticSearchProperties.getUrl())
                .usingSsl(elasticSearchProperties.getFingerprint())
                .withBasicAuth(elasticSearchProperties.getUsername(), elasticSearchProperties.getPassword())
                .build();
    }
}
