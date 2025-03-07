package top.guke.ai2service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope  // 支持动态刷新
public class ConfigTestController {

    // 从 Nacos 配置中心读取的配置
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${oss.endpoint}")
    private String ossEndpoint;

    @Value("${deepseek.api-key}")
    private String deepSeekApiKey;

    @Autowired
    private JdbcTemplate jdbcTemplate;  // 注入 JdbcTemplate

    // 测试接口：返回所有配置并分别写入数据库
    @GetMapping("/test")
    public String testConfig() {
        // 将每条配置信息分别写入数据库
        saveConfigToDatabase("Database URL", dbUrl);
        saveConfigToDatabase("Redis Host", redisHost);
        saveConfigToDatabase("OSS Endpoint", ossEndpoint);
        saveConfigToDatabase("DeepSeek API Key", deepSeekApiKey);

        // 返回所有配置信息，每条配置信息占一行
        return String.format(
                "Database URL: %s\n\nRedis Host: %s\n\nOSS Endpoint: %s\n\nDeepSeek API Key: %s",
                dbUrl, redisHost, ossEndpoint, deepSeekApiKey
        );
    }

    // 使用 JdbcTemplate 将单条配置信息写入数据库
    private void saveConfigToDatabase(String configType, String configContent) {
        String sql = "INSERT INTO config_test (config_type, config_content) VALUES (?, ?)";
        jdbcTemplate.update(sql, configType, configContent);
    }
}