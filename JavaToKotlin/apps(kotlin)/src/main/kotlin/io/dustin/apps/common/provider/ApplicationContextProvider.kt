package io.dustin.apps.common.provider

import org.springframework.context.ApplicationContext

/**
 * ApplicationContextAware 을 이용해서 ApplicationContext 를 제공하는 제공자를 만든다.
 *
 * 인스턴스가 조작되는 것을 private static final 로 정의된 inner class 통해 감싸서 제공한다.
 *
 */
@Component
class ApplicationContextProvider : ApplicationContextAware {
    private object ApplicationContextHolder {
        private val INNER_PRIVATE_RESOURCE: InnerPrivateResource? = InnerPrivateResource()
    }

    /**
     * 내부에서만 ApplicationContext에 접근가능하게
     */
    private class InnerPrivateResource private constructor() {
        private var context: ApplicationContext? = null
        private fun setContext(context: ApplicationContext?) {
            this.context = context
        }
    }

    @Override
    fun setApplicationContext(context: ApplicationContext?) {
        ApplicationContextHolder.INNER_PRIVATE_RESOURCE.setContext(context)
    }

    companion object {
        fun getApplicationContext(): ApplicationContext? {
            return ApplicationContextHolder.INNER_PRIVATE_RESOURCE.context
        }
    }
}