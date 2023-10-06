package io.dustin.common.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.web.reactive.config.WebFluxConfigurer

@EnableR2dbcRepositories
@Configuration
class WebConfiguration: WebFluxConfigurer {


}