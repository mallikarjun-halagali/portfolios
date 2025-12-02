//package com.mallikarjun.user_mngt.config;
//
//import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
////import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.Executors;
//
//@Configuration
//public class TomcatVirtualThreadConfig {
//
//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
//        return factory -> factory.addConnectorCustomizers(connector -> {
//            var executor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory());
//            connector.getProtocolHandler().setExecutor(executor);
//        });
//    }
//}
