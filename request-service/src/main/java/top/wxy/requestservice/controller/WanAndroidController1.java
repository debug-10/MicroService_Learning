package top.wxy.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WanAndroidController1 {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/wanRestTemplate")
    public ResponseEntity<String> getArticlesWithRestTemplate() {
        String url = "https://www.wanandroid.com/article/list/0/json";
        String response = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(response);
    }
}