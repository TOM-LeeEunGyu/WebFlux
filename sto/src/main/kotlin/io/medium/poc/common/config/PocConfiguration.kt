package io.medium.poc.common.config

import io.medium.poc.common.cache.CustomInvestorCacheManager
import io.medium.poc.common.utils.logger
import io.medium.poc.domain.com.model.dto.ComInvestorDto
import io.medium.poc.domain.com.service.ReadComInvestorService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@Configuration
class PocConfiguration(
    private val readComInvestorService: ReadComInvestorService,
    private val comInvestorCache: CustomInvestorCacheManager,
) {
    private val log = logger<PocConfiguration>()

    @EventListener(ApplicationReadyEvent::class)
    fun initInvestorCache() {
        log.info("Com Investor Cache start...")
        val comInvestors = readComInvestorService.comInvestors()
        comInvestors.forEach { comInvestor ->
            comInvestorCache.cachedComInvestor(comInvestor.investorId, ComInvestorDto.toDto(comInvestor))
        }
        log.info("Com Investor Cache end...")
    }

}
