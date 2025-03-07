package top.guke.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "guke")
@Data
public class GukeProperties {
    private String username;
    private String job;
    private boolean serviceFlag;
}
