package com.filarty.zoomarket.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "elastic")
public class ElasticSearchProperties {
    private String username;
    private String password;
    private String url;
    private String fingerprint;
}
