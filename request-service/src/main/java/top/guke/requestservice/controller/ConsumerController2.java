package top.guke.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerController2 {
    @Resource
    private RestTemplate restTemplate;
    private final String SERVICE_URL = "http://localhost:8080";


    @GetMapping("/restTemplateTest")
    public String restTemplateTest() {
        return restTemplate.getForObject(SERVICE_URL + "/hello", String.class);
    }


}
