package io.dustin.apps.common.config

import lombok.extern.slf4j.Slf4j
@Slf4j
@Configuration
@Profile("redis")
class RedisCacheConfiguration {
    init {
        log.info("RedisCacheConfiguration init...")
    }
}
