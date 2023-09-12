package io.medium.poc.common.cache

import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.com.model.dto.ComInvestorDto
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomInvestorCacheManager {

    private val cache = ConcurrentHashMap<String, ComInvestorDto>()

    fun cachedComInvestor(key: String, value: ComInvestorDto) {
        cache[key] = value
    }

    fun cacheEvict(key: String) {
        cache.remove(key)
    }

    fun cacheGet(key: String): ComInvestorDto {
        return cache[key] ?: notFoundEntity("캐시 정보가 없습니다.")
    }

    fun comInvestors(): List<ComInvestorDto> {
        return cache.values.toList()
    }

}
