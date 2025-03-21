package top.guke.gatewayservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义全局错误属性类，用于统一处理网关异常并返回标准化的错误信息。
 * 该类继承自 Spring Boot 的 DefaultErrorAttributes 类，并重写了 getErrorAttributes 方法，以实现自定义错误信息的封装和日志记录。
 * @author moqi
 */
@Slf4j
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    /**
     * 重写 getErrorAttributes 方法，用于自定义错误信息的封装。
     * 当网关发生异常时，该方法会被调用，并返回一个包含错误信息的 Map。
     *
     * @param request 当前请求的 ServerRequest 对象，包含请求的上下文信息。
     * @param options 错误属性选项，用于控制错误信息的详细程度。
     * @return 包含错误信息的 Map，其中包含错误代码和错误消息。
     */
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        // 调用父类方法获取原始错误信息
        Throwable error = super.getError(request);
        // 记录异常日志，方便排查问题
        log.error("网关处理异常", error);
        // 创建一个 Map 用于封装自定义错误信息
        Map<String, Object> errorMap = new HashMap<>();
        // 设置错误消息为异常的详细信息
        errorMap.put("code", HttpStatus.BAD_REQUEST.value());
        errorMap.put("msg", error.getMessage());
        return errorMap;
    }

}