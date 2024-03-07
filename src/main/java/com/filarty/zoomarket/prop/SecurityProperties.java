package com.filarty.zoomarket.prop;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
    private String adminName;
    private String adminPassword;
    private String secretKey;
}

