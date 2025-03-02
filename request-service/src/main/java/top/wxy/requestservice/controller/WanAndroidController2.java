package top.wxy.requestservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class WanAndroidController2 {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://www.wanandroid.com")
            .build();

    @GetMapping("/wanWebClient")
    public ResponseEntity<String> getArticlesWithWebClient(@RequestParam int page) {
        Mono<String> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/article/list/{page}/json")
                        .build(page))
                .retrieve()
                .bodyToMono(String.class);

        String response = responseMono.block();
        return ResponseEntity.ok(response);
    }
}