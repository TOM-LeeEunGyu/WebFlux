package io.dustin.apps.common.cache

import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto
import io.dustin.apps.common.exception.DataNotFoundException
import io.dustin.apps.common.utils.dataNotFound
import io.dustin.apps.common.utils.notFoundEntity
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomPostingCacheManager {

    private val cache = ConcurrentHashMap<String, PostingDto>()

    fun cachedPostingInvestor(key: Long, value: PostingDto) {
        cache[key.toString()] = value
    }

    fun cacheEvict(key: Long) {
        cache.remove(key.toString())
    }

    fun cacheGet(key: Long): PostingDto {
        return cache[key.toString()] ?: dataNotFound("캐시 정보가 없습니다.")
    }

    fun comInvestors(): List<PostingDto> {
        return cache.values.toList()
    }


}