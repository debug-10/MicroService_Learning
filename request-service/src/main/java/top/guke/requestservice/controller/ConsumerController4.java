package top.guke.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.guke.requestservice.openfeign.HelloService;

@RestController
public class ConsumerController4 {
    @Resource
    private HelloService helloService;

    @GetMapping("/hi")
    public String sayhi(@RequestParam String name) {
        return helloService.hi(name);
    }
}
