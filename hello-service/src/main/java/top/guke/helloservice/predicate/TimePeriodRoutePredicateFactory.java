package top.guke.helloservice.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import top.guke.helloservice.exception.TimePeriodMismatchException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class TimePeriodRoutePredicateFactory extends AbstractRoutePredicateFactory<TimePeriodRoutePredicateFactory.Config> {

    public TimePeriodRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("startTime", "endTime");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            LocalTime now = LocalTime.now();
            if (config.getStartTime() == null || config.getEndTime() == null) {
                throw new IllegalArgumentException("Start time and end time must not be null");
            }
            if (now.isBefore(config.getStartTime()) || now.isAfter(config.getEndTime())) {
                throw new TimePeriodMismatchException("当前时间不符");
            }
            return true;
        };
    }

    public static class Config {
        private LocalTime startTime;
        private LocalTime endTime;

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = parseTime(startTime);
        }

        public LocalTime getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = parseTime(endTime);
        }

        private LocalTime parseTime(String time) {
            if (time == null || time.isEmpty()) {
                throw new IllegalArgumentException("Time must not be null or empty");
            }
            DateTimeFormatter formatter = time.length() <= 5
                    ? DateTimeFormatter.ofPattern("H:mm")
                    : DateTimeFormatter.ofPattern("HH:mm:ss");
            return LocalTime.parse(time, formatter);
        }
    }
}