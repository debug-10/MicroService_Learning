package top.guke.aiuserservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ai-service")
public interface AIClient {

    @PostMapping("/ai/ask")
    String askQuestion(@RequestParam String question);
}