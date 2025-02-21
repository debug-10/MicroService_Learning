package top.guke.productservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    @GetMapping("/product")
    public String getProduct(@RequestParam String productname) {
        return "Product: " + productname;
    }
}
