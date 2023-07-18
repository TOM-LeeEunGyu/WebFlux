package io.dustin.apps.common.config

import io.dustin.apps.common.resolver.CustomTestResolver

@Configuration
class WebConfig : WebMvcConfigurer {
    @Override
    fun addArgumentResolvers(resolvers: List<HandlerMethodArgumentResolver?>?) {
        resolvers.add(customTestResolver())
    }

    @Bean
    fun customTestResolver(): CustomTestResolver? {
        return CustomTestResolver()
    }
}
