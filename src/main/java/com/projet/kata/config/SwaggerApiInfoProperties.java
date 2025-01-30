package com.projet.kata.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * configuration class that loads swagger properties
 */
@ConfigurationProperties(prefix = "swagger", ignoreInvalidFields = true)
@Data
public class SwaggerApiInfoProperties {

    @Valid
    private Map<String, ApiInfoProperties> groups;

    @Valid
    private ApiInfoProperties defaultApiInfo;

    public ApiInfoProperties getGroupApiInfo(String groupName) {
        return this.groups.get(groupName);
    }

    @Data
    public static class ApiInfoProperties {

        private String title;
        private String description;
        private String termsOfServiceUrl;
        private String license;
        private String licenseUrl;
        private String version;
        @Valid
        private Contact contact;
        @Data
        public static class Contact {
            @NotNull
            private String name;
            private String url;
            private String email;
        }
    }
}
