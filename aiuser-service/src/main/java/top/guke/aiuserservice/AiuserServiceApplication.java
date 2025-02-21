package top.guke.aiuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AiuserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiuserServiceApplication.class, args);
    }

}
