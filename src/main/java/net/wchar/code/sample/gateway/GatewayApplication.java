package net.wchar.code.sample.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

/**
 * @author wchar.net
 */
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(GatewayApplication.class, args);
        //刷新路由
        //( @Autowired ApplicationEventPublisher)   applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

}
