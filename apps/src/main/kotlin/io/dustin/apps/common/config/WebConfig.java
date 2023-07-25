//package io.dustin.apps.common.config;
//
//import io.dustin.apps.common.resolver.CustomTestResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(customTestResolver());
//    }
//
//    @Bean
//    public CustomTestResolver customTestResolver() {
//        return new CustomTestResolver();
//    }
//
//}
