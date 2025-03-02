package top.guke.requestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WanAndroidController2 {

    private final String SERVICE_URL = "https://www.wanandroid.com";

    private final WebClient webClient = WebClient.builder().baseUrl(SERVICE_URL).build();

    @GetMapping("/webClientAndroid")
    public Mono<String> webClientAndroid(@RequestParam int page) {
        // 修改 URI，正确地使用路径参数
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/article/list/{page}/json")
                        .build(page))  // 传递 page 作为路径变量
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    return response;  // 返回接口的响应内容
                });
    }
}
