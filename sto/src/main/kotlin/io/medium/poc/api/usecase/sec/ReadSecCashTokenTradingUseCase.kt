package io.medium.poc.api.usecase.sec

import io.medium.poc.api.controller.sec.request.query.SecCashTokenTradingQuery
import io.medium.poc.domain.sec.model.dto.SecCashTokenTradingDto
import io.medium.poc.domain.sec.service.ReadSecService
import org.springframework.stereotype.Service

@Service
class ReadSecCashTokenTradingUseCase(
    private val readSecService: ReadSecService,
) {

    fun execute(query: SecCashTokenTradingQuery): List<SecCashTokenTradingDto> {
        return with(query) {
            readSecService.cashTokenTradingList(compositeId(), start(), end())
                          .map { entity -> SecCashTokenTradingDto.toDto(entity) }
        }
    }

}
