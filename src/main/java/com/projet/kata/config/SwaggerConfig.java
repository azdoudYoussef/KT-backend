package com.projet.kata.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * configuration class of Swagger documentation
 */
@Configuration
@OpenAPIDefinition
@PropertySource(value = {"classpath:swagger-labels.properties"})
@EnableConfigurationProperties(SwaggerApiInfoProperties.class)
public class SwaggerConfig {

    @Bean
    public OpenAPI producerPrivateApis(SwaggerApiInfoProperties properties) {
        final String securitySchemeName = "Bearer Token";
        var informations = properties.getGroupApiInfo("private");

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        ))
                .info(new Info().title(informations.getTitle())
                        .description(informations.getDescription())
                        .version(informations.getVersion())
                        .license(new License().name(informations.getLicense())
                                .url(informations.getLicenseUrl())));

    }
}
