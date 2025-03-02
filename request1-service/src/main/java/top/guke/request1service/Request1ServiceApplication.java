package top.guke.request1service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Request1ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Request1ServiceApplication.class, args);
    }

}
