package com.aatreya.inventorymgmt.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.core.convert.MappingCosmosConverter;
import com.azure.spring.data.cosmos.core.mapping.CosmosMappingContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.mockito.Mockito;

class CosmosRepositoryConfigTests {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner().withUserConfiguration(CosmosRepositoryConfig.class);

    @Test
    void configEnabled_registersConfigurationBean() {
        contextRunner
            .withPropertyValues("spring.cloud.azure.cosmos.enabled=true")
                .withBean("cosmosTemplate", CosmosTemplate.class, this::mockCosmosTemplate)
            .run(context -> assertThat(context).hasSingleBean(CosmosRepositoryConfig.class));
    }

    @Test
    void configDisabled_doesNotRegisterConfigurationBean() {
        contextRunner
            .withPropertyValues("spring.cloud.azure.cosmos.enabled=false")
            .run(context -> assertThat(context).doesNotHaveBean(CosmosRepositoryConfig.class));
    }

    private CosmosTemplate mockCosmosTemplate() {
        CosmosTemplate template = Mockito.mock(CosmosTemplate.class);
        MappingCosmosConverter converter = Mockito.mock(MappingCosmosConverter.class);
        CosmosMappingContext mappingContext = new CosmosMappingContext();
        Mockito.when(converter.getMappingContext()).thenReturn((org.springframework.data.mapping.context.MappingContext) (Object) mappingContext);
        Mockito.when(template.getConverter()).thenReturn(converter);
        return template;
    }
}