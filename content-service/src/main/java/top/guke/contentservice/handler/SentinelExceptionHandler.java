package top.guke.contentservice.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class SentinelExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {

        String msg = "未知异常";
        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        log.error("Sentinel 异常" , e);

        if(e instanceof FlowException) {
            msg = "请求被限流了";
        }else if(e instanceof DegradeException) {
            msg = "请求被降级了";
        } else if (e instanceof AuthorityException) {
            msg = "没有权限访问";
        }

        String jsonString= """
        {
        "code":"%s",
        "msg":"%s"
        }
        """.formatted(code,msg);

        response.setContentType("application/json;charset=utf-8");
        response.setStatus(code);
//        response.getWriter().println("{\"msg\": \"" + msg + "\", \"code\": " + code + "}");
        response.getWriter().write(jsonString);
    }
}
