package com.logicoy.api.productsearch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Swagger / OpenAPI configuration.
 
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productSearchOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Product Search API")
                .description("API contract for product search")
                .version("v1"));
    }
}
