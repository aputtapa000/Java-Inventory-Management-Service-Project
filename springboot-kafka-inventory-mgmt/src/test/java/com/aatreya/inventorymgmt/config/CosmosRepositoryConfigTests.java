package com.aatreya.inventorymgmt.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest(classes = CosmosRepositoryConfig.class, properties = "spring.cloud.azure.cosmos.enabled=true")
class CosmosRepositoryConfigTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void configEnabled_registersConfigurationBean() {
        assertThat(applicationContext.getBean(CosmosRepositoryConfig.class)).isNotNull();
    }

    @Test
    void configDisabled_doesNotRegisterConfigurationBean() {
        ApplicationContextRunner runner = new ApplicationContextRunner()
                .withUserConfiguration(CosmosRepositoryConfig.class)
                .withPropertyValues("spring.cloud.azure.cosmos.enabled=false");

        runner.run(context -> assertThat(context).doesNotHaveBean(CosmosRepositoryConfig.class));
    }
}