package com.aatreya.inventorymgmt.config;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.cloud.azure.cosmos", name = "enabled", havingValue = "true")
@EnableCosmosRepositories(basePackages = "com.aatreya.inventorymgmt.repository")
public class CosmosRepositoryConfig {
}
