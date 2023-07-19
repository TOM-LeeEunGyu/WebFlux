package io.dustin.apps.common.resolver;

import io.dustin.apps.common.annotations.AuthToken;
import io.dustin.apps.common.exception.BadRequestParameterException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CustomTestResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String bearerToken = webRequest.getHeader("Authorization");// Bearer xxxxxxxxxxx Authorization
        if(bearerToken == null) {
            throw new BadRequestParameterException("헤더에 토큰 정보가 없습니다.");
        }

        if(!bearerToken.startsWith("Bearer")) {
            throw new BadRequestParameterException("유효하지 않은 토큰이야!");
        }
        // validation
        // if invalid -> throws error
        return bearerToken.split(" ")[1];
    }
}
