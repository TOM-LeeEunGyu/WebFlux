package io.dustin.apps.common.properties

import lombok.Data

@Data
@Component
@ConfigurationProperties(prefix = "rigo")
class RigoProperties {
    private val nodeUrl: String? = null
    private val nodeSocket: String? = null
}
