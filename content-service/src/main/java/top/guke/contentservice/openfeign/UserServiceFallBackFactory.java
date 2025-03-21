package top.guke.contentservice.openfeign;

import com.alibaba.nacos.api.model.v2.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.guke.contentservice.model.vo.UserVo;

@Slf4j
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable cause) {
        log.error("用户服务调用异常:", cause);
        return new UserFeignClient() {
            @Override
            public Result<UserVo> getUserById(int id) {
                UserVo userVo = new UserVo();
                userVo.setId(-1);
                userVo.setUserName("异常的用户名");
                userVo.setAvatarUrl("https://my-wxy-bucket.oss-cn-nanjing.aliyuncs.com/picture/liang.jpg");
                return Result.success(userVo);
            }
        };
    }
}