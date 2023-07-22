import io.dustin.apps.board.domain.like.model.LikeCountService
import io.dustin.apps.common.provider.ApplicationContextProvider
import org.springframework.stereotype.Component

@Component
class CommonUtil {

    companion object {
        inline fun <reified T> getBean(beanId: String, java: Class<LikeCountService>): T {
            val applicationContext = ApplicationContextProvider.applicationContext;
            return applicationContext.getBean(beanId, T::class.java)
        }
    }
}
