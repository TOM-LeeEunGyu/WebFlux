package io.dustin.apps.common.config

import lombok.extern.slf4j.Slf4j
@Slf4j
@Configuration
@Profile("local cache")
class LocalCacheConfiguration {
    init {
        log.info("LocalCacheCacheConfiguration init...")
    }
}
