package top.guke.orderservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/order")
    public String creatOrder( @RequestParam String orderId ,@RequestParam String username ,@RequestParam String productname ) {
        String userServiceUrl = "http://localhost:8081/user?username=" + username;
        String userInfo =  restTemplate.getForObject(userServiceUrl, String.class);

        String productServiceUrl = "http://localhost:8083/product?productname=" + productname;
        String productInfo =  restTemplate.getForObject(productServiceUrl, String.class);


        return "订单编号是：" + orderId + "  下单人是：" + userInfo + "  商品信息："+ productInfo;
    }

}
