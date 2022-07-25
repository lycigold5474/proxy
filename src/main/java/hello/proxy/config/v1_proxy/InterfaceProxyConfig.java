package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logtrace){
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logtrace)); //client -> proxy ->controller
        return new OrderControllerInterfaceProxy(controllerImpl, logtrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logtrace) {
        OrderServiceV1Impl orderServiceImpl = new OrderServiceV1Impl(orderRepository(logtrace));
        return new OrderServiceInterfaceProxy(orderServiceImpl, logtrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logtrace) {
        OrderRepositoryV1Impl orderRepositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryImpl, logtrace);
    }
}
