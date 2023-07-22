package io.dustin.apps.common.provider

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * ApplicationContextAware 을 이용해서 ApplicationContext 를 제공하는 제공자를 만든다.
 *
 * 인스턴스가 조작되는 것을 private static final 로 정의된 inner class 통해 감싸서 제공한다.
 *
 */
@Component
class ApplicationContextProvider : ApplicationContextAware {

    /**
     * 내부에서만 ApplicationContext에 접근가능하게
     */
    private object ApplicationContextHolder {
        lateinit var context: ApplicationContext
    }

    override fun setApplicationContext(context: ApplicationContext) {
        ApplicationContextHolder.context = context
    }

    companion object {
        val applicationContext: ApplicationContext
            get() = ApplicationContextHolder.context
    }
}
