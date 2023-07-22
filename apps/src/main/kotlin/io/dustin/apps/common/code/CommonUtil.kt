package io.dustin.apps.common.code

import io.dustin.apps.common.provider.ApplicationContextProvider
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
object CommonUtil {
    fun <T : Any> getBean(beanId: String, clazz: KClass<T>): T {
        val applicationContext = ApplicationContextProvider.applicationContext()
            ?: throw NullPointerException("Not Initialized Spring Container!")
        return applicationContext.getBean(beanId, clazz.java)
    }
}
