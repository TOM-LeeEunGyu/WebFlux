package io.medium.poc.api.usecase.sec

import io.medium.poc.domain.sec.model.dto.SecCustMgmtInstCashTokenStateDto
import io.medium.poc.domain.sec.model.entity.SecCustMgmtInstCashTokenStateMultiKeys
import io.medium.poc.domain.sec.service.ReadSecService
import org.springframework.stereotype.Service

@Service
class ReadSecCustMgmtInstCashTokenStateUseCase(
    private val readSecService: ReadSecService,
) {

    fun execute(compositeId: SecCustMgmtInstCashTokenStateMultiKeys): SecCustMgmtInstCashTokenStateDto {
        val result = readSecService.custMgmtInstCashTokenStateById(compositeId)
        return SecCustMgmtInstCashTokenStateDto.toDto(result)
    }

}
