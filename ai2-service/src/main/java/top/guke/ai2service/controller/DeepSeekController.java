package top.guke.ai2service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/deepseek")
@RefreshScope  // 支持动态刷新
public class DeepSeekController {

    @Value("${deepseek.api-key}")
    private String deepSeekApiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // 调用 DeepSeek API
    @GetMapping("/test")
    public Mono<String> testDeepSeek(@RequestParam(required = false, defaultValue = "Hello") String message) {
        WebClient webClient = WebClient.create("https://api.deepseek.com");

        // 构建请求体
        String requestBody = """
            {
                "model": "deepseek-chat",
                "messages": [
                    {
                        "role": "user",
                        "content": "%s"
                    }
                ]
            }
            """.formatted(message);

        return webClient.post()
                .uri("/v1/chat/completions")
                .header("Authorization", "Bearer " + deepSeekApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::extractDeepSeekResponse);  // 提取 DeepSeek 的回答
    }

    // 提取 DeepSeek 的回答
    private String extractDeepSeekResponse(String jsonResponse) {
        try {
            // 将 JSON 字符串解析为 JsonNode
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            // 提取回答内容
            return rootNode.path("choices")
                    .path(0)
                    .path("message")
                    .path("content")
                    .asText();
        } catch (Exception e) {

            return "Failed to parse DeepSeek response: " + e.getMessage();
        }
    }
}