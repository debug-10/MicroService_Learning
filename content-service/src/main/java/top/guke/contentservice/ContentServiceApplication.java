package top.guke.contentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.guke.contentservice.config.RandomLoadBalancerConfig;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name="user-service",configuration = RandomLoadBalancerConfig.class)
public class ContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApplication.class, args);
    }

}
