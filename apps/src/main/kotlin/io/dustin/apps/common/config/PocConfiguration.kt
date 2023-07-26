package io.dustin.apps.common.config

import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService
import io.dustin.apps.common.utils.logger
import io.medium.poc.common.cache.CustomPostingCacheManager
import org.springframework.context.annotation.Configuration


/**
 * 자주 사용되고 변경이 적은 데이터를 db에 직접 접근하지 말고 캐싱해보자
 * 게시물 데이터는 위의 요구사항과 완벽히 부합한다고 할 수 없다. 하지만 일단 임시로 작성해놓은 것
 */
@Configuration
class PocConfiguration(
    private val readPostingService: ReadPostingService,
    private val postingCache: CustomPostingCacheManager,
) {
    private val log = logger<PocConfiguration>()

    // temp
    val userId: Long = 1L
    val postingId: Long = 1L
    val nextId: Long = 1L
    val size:Long = 1L


    init {
        log.info("Com Investor Cache start...")
        val postingList = readPostingService.TestfindAll()
        postingList.forEach { posting ->
            posting.id?.let { postingCache.cachedPostingInvestor(it, PostingDto.from(posting)) }
        }
        log.info("Com Investor Cache end...")
    }

}