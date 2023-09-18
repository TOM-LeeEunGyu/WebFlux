package io.dustin.apps.common.aop

import io.dustin.apps.common.properties.JwtProperties
import io.dustin.apps.common.utils.extractToken
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class AuthorizedAOP(
    private val props: JwtProperties,
) {

    @Around("@annotation(io.dustin.apps.common.aop.Authorized)")
    fun authorized(joinPoint: ProceedingJoinPoint): Any {
        val args = joinPoint.args.map {
            when(it) {
                is String -> {
                    if(it.startsWith(props.prefix)) {
                        extractToken(it, props)
                    } else {
                        it
                    }
                }
                else -> it
            }
        }
        return joinPoint.proceed(args.toTypedArray())
    }

}