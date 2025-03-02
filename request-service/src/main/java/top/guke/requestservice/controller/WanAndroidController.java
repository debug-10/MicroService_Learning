package top.guke.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
public class WanAndroidController {

    @Resource
    private RestTemplate restTemplate;

    private final String SERVICE_URL = "https://www.wanandroid.com";

    @GetMapping("/restTemplateAndroid")
    public String restTemplateAndroid() throws IOException {
        return restTemplate.getForObject(SERVICE_URL + "/article/list/1/json", String.class);

    }
}
