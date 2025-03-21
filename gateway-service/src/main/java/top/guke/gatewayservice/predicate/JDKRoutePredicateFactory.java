package top.guke.gatewayservice.predicate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class JDKRoutePredicateFactory extends AbstractRoutePredicateFactory<JDKRoutePredicateFactory.Config> {

    public static final String VERSION = "version";

    public JDKRoutePredicateFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(VERSION);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String version = System.getProperty("java.version");
                return StringUtils.isNotBlank(version) & version.startsWith(config.getVersion());
            }

            @Override
            public Object getConfig() {
                return config;
            }

            @Override
            public String toString() {
                return String.format("JDK Version: %s", config.getVersion());
            }
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        @NotNull
        private String version;
    }
}
