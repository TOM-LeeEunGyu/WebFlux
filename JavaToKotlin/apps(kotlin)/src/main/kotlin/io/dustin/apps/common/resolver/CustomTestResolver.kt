package io.dustin.apps.common.resolver

import io.dustin.apps.common.annotations.AuthToken

class CustomTestResolver : HandlerMethodArgumentResolver {
    @Override
    fun supportsParameter(parameter: MethodParameter?): Boolean {
        return parameter.hasParameterAnnotation(AuthToken::class.java)
    }

    @Override
    @Throws(Exception::class)
    fun resolveArgument(
        parameter: MethodParameter?,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest?,
        binderFactory: WebDataBinderFactory?
    ): Object? {
        val bearerToken: String = webRequest.getHeader("Authorization")
            ?: throw BadRequestParameterException("헤더에 토큰 정보가 없습니다.") // Bearer xxxxxxxxxxx Authorization
        if (!bearerToken.startsWith("Bearer")) {
            throw BadRequestParameterException("유효하지 않은 토큰이야!")
        }
        // validation
        // if invalid -> throws error
        return bearerToken.split(" ").get(1)
    }
}
