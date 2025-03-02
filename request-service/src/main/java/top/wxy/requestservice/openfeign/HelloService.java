package top.wxy.requestservice.openfeign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 笼中雀
 */
@FeignClient(value = "hello-service")
public interface HelloService {
    @GetMapping(value = "/sayHello")
     String hi(@RequestParam String name);
}
