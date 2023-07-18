package io.dustin.apps.common.config

import io.dustin.apps.common.properties.RigoProperties

@Configuration
@RequiredArgsConstructor
class RigoWeb3Configuration {
    private val prop: RigoProperties? = null
    @Bean
    fun rigoClient(): RigoWeb3? {
        val factory: RigoApiClientFactory = RigoApiClientFactory.newInstance(prop.getNodeUrl())
        return factory.rigoWeb3()
    }
}
