package io.dustin.apps.common.config

import feign.Retryer
import feign.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * open feign client configuration
 * created by basquiat
 */
@Configuration
class FeignClientConfig {

    /**
     * logger level setup
     * @return Logger.Level
     */
    @Bean
    fun loggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    /**
     * 재시도는 3번 정도만 한다.
     * 기본은 5
     * @return Retryer
     */
    @Bean
    fun retryer(): Retryer {
        return Retryer.Default(2000, 2000, 3)
    }
}