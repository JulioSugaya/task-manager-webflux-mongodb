package com.sugaya.task_manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Info information = new Info()
                .title("Task Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage tasks.");
        return new OpenAPI().info(information);
    }
}