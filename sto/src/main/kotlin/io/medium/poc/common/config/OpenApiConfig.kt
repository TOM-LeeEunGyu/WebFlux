package io.medium.poc.common.config

import io.medium.poc.common.filter.MethodFilter
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Open API configuration
 * created by basquiat
 */
@Configuration
class OpenApiConfig {

    @Bean
    fun groupedOpenApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
                             .group("IBK STO POC Backend Server")
                             .pathsToMatch("/api/poc/**")
                             .build()
    }

    @Bean
    fun openAPI(): OpenAPI {
        val info = Info().title("IBK STO POC API")
                               .version("v3")
                               .description("IBK STO POC 를 위한 백엔드 API 서버")
        return OpenAPI().components(Components())
                        .info(info)
    }

    @Bean
    fun methodFilter(): FilterRegistrationBean<MethodFilter> {
        val registrationBean: FilterRegistrationBean<MethodFilter> = FilterRegistrationBean<MethodFilter>()
        registrationBean.filter = MethodFilter()
        registrationBean.addUrlPatterns("/api/poc/*")
        return registrationBean
    }
}
