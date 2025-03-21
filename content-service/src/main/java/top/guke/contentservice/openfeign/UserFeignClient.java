package top.guke.contentservice.openfeign;

import com.alibaba.nacos.api.model.v2.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.guke.contentservice.model.DTO.UserDTO;
import top.guke.contentservice.model.vo.UserVo;



@FeignClient(name = "user-service", fallbackFactory = UserServiceFallBackFactory.class)
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    Result<UserVo> getUserById(@PathVariable int id);
}