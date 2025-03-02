package top.guke.helloservice.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getHello() {
        return "Hello World";
    }
    public String sayhello(String name) {
        return "hello " + name;
    }


}
