package top.guke.contentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import top.guke.contentservice.config.RandomLoadBalancerConfig;
import top.guke.contentservice.handler.SentinelConfig;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name="user-service",configuration = RandomLoadBalancerConfig.class)
@Import({SentinelConfig.class})
public class ContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApplication.class, args);
    }

}
