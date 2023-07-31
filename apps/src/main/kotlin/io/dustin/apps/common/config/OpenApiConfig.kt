package io.dustin.apps.common.config

import io.dustin.apps.common.filter.MethodFilter
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfig {

    @Bean
    fun groupedOpenApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
                             .group("dustin-app Backend Server")
                             .pathsToMatch("/api/v1/**")
                             .build()
    }

    @Bean
    fun openAPI(): OpenAPI {
        val info = Info().title("dustin's app API")
                               .version("v1")
                               .description("dustin's app 백엔드 API 서버")
        return OpenAPI().components(Components())
                        .info(info)
    }

    @Bean
    fun methodFilter(): FilterRegistrationBean<MethodFilter> {
        val registrationBean: FilterRegistrationBean<MethodFilter> = FilterRegistrationBean<MethodFilter>()
        registrationBean.filter = MethodFilter()
        registrationBean.addUrlPatterns("/api/v1/")
        return registrationBean
    }
}