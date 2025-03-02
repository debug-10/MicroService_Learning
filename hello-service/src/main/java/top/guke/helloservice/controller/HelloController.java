package top.guke.helloservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.guke.helloservice.service.HelloService;

@RestController
public class HelloController {
    @Resource
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(){
        return "hello from :" + helloService.getHello();
    }

    @GetMapping("/sayhello")
    public String sayhello(@RequestParam String name){
        return helloService.sayhello(name);
    }

}
