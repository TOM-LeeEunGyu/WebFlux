//package io.dustin.apps.common.config;
//
//import io.dustin.apps.common.properties.RigoProperties;
//import io.rigo.sdk.common.client.RigoWeb3;
//import io.rigo.sdk.common.client.factory.RigoApiClientFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class RigoWeb3Configuration {
//
//    private final RigoProperties prop;
//
//    @Bean
//    public RigoWeb3 rigoClient() {
//        RigoApiClientFactory factory = RigoApiClientFactory.newInstance(prop.getNodeUrl());
//        return factory.rigoWeb3();
//    }
//}
