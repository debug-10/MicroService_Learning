package top.guke.ai2service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}