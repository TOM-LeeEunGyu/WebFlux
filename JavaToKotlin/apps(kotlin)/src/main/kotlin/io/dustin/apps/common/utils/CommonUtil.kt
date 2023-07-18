package io.dustin.apps.common.utils

import io.dustin.apps.common.provider.ApplicationContextProvider

@Component
object CommonUtil {
    fun <T> getBean(beanId: String?, clazz: Class<T?>?): T? {
        val applicationContext: ApplicationContext = ApplicationContextProvider.getApplicationContext()
            ?: throw NullPointerException("Not Initialize Spring Container!")
        return applicationContext.getBean(beanId) as T
    }
}
