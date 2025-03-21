package top.guke.gatewayservice.handler;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 全局错误处理器，用于统一处理网关中的异常并返回标准化的错误响应。
 * 该类继承自 Spring Boot 的 AbstractErrorWebExceptionHandler 类，并重写了相关方法，以实现自定义错误响应的逻辑。
 * @author moqi
 */
@Component
@Order(-2) // 设置优先级，确保该异常处理器优先于其他异常处理器生效
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    /**
     * 构造函数，初始化全局错误处理器。
     *
     * @param gea                  自定义的错误属性类，用于获取错误信息。
     * @param applicationContext   Spring 应用上下文，用于获取配置信息。
     * @param serverCodecConfigurer 用于配置编码器和解码器，以支持 JSON 等格式的响应。
     */
    public GlobalExceptionHandler(GlobalErrorAttributes gea, ApplicationContext applicationContext,
                                  ServerCodecConfigurer serverCodecConfigurer) {
        // 调用父类构造函数
        super(gea, new WebProperties.Resources(), applicationContext);
        // 设置响应消息的编码器
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        // 设置请求消息的解码器
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    /**
     * 获取路由函数，用于定义错误响应的路由规则。
     * 该方法重写了父类的方法，返回一个路由函数，该函数将所有请求映射到 renderErrorResponse 方法。
     *
     * @param errorAttributes 错误属性类，用于获取错误信息。
     * @return 路由函数，定义了错误响应的路由规则。
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        // 将所有请求映射到 renderErrorResponse 方法
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }


    /**
     * 渲染错误响应的方法。
     * 该方法根据请求中的错误信息，生成一个标准化的错误响应。
     *
     * @param request 当前请求的 ServerRequest 对象，包含请求的上下文信息。
     * @return 一个 Mono<ServerResponse> 对象，表示响应的异步操作。
     */
    private Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
        // 获取错误属性
        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        // 构建错误响应
        return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }

}