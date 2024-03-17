package com.chl;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/8
 */
public class FluxServer {
    //1 创建 Router 路由
    public RouterFunction<ServerResponse> routingFunction() {
        //创建 hanler 对象
        ProductHandler handler = new ProductHandler();
        //设置路由
        return RouterFunctions.route(
                        GET("/product/{id}").and(accept(APPLICATION_JSON)), handler::getProductById)
                .andRoute(GET("/product").and(accept(TEXT_EVENT_STREAM)), handler::getAllProducts)
                .andRoute(POST("/saveProduct").and(accept(APPLICATION_JSON)), handler::saveProduct);
    }

    //2 创建服务器完成适配
    public void createReactorServer() {
        //路由和 handler 适配
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new
                ReactorHttpHandlerAdapter(httpHandler);
        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.port(12300);
        httpServer.handle(adapter).bindNow();
    }

    //最终调用
    public static void main(String[] args) throws Exception {
        FluxServer server = new FluxServer();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }
}
