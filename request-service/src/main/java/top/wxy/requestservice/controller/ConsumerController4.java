package top.wxy.requestservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.wxy.requestservice.openfeign.HelloService;

@RestController
public class ConsumerController4 {
    @Resource
    private HelloService helloService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam String name){
        return helloService.hi(name);
    }
}
