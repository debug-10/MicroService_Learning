package top.guke.gatewayservice.filter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class InvalidParameterGatewayFilterFactory extends AbstractGatewayFilterFactory<InvalidParameterGatewayFilterFactory.Config> {

    public static final String NAME = "name";
    public static final String VALUE = "value";

    public InvalidParameterGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME, VALUE);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (StringUtils.isNotBlank(config.getName()) && StringUtils.isNotBlank(config.getValue())) {
                ServerHttpRequest request = exchange.getRequest();
                String value = request.getQueryParams().get(config.getName()).get(0);
                if (config.getValue().equalsIgnoreCase(value)) {
                    throw new RuntimeException("非法参数");
                }
            }
            return chain.filter(exchange);
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        private String name;
        private String value;
    }
}
