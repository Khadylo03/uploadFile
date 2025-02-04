package org.sonatel.uploadfile.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.sonatel.uploadfile.domain.enums.StorageType;
import org.sonatel.uploadfile.service.interfaces.IStorageStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@OpenAPIDefinition(info = @Info(
        title = "Your API Title",
        version = "v1"),
        security = @SecurityRequirement(name = "bearerAuth"))

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT")

@Configuration
public class AppConfig {
    @Bean
    public  Map<StorageType, IStorageStrategy> getStrategies(ApplicationContext applicationContext){
        Map<StorageType, IStorageStrategy> strategies = new HashMap<>();
        Map<String, IStorageStrategy> defaultStrategies = applicationContext.getBeansOfType(IStorageStrategy.class);
        for(IStorageStrategy strategy : defaultStrategies.values()){
            StorageType storageType = strategy.getStorageType();
            strategies.put(storageType, strategy);
        }
        return strategies;
    }
}
