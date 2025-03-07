package top.guke.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    @Value("${guke.username}")
    private String username;

    @Value("${guke.job}")
    private String job;

    @GetMapping("/test")
    public String test() {
        return "读取" + username  + job;
    }
}
