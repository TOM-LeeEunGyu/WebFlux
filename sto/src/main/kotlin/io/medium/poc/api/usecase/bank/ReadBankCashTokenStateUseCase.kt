package io.medium.poc.api.usecase.bank

import io.medium.poc.domain.bank.model.dto.BankCashTokenStateDto
import io.medium.poc.domain.bank.model.entity.BankCashTokenStateMultiKeys
import io.medium.poc.domain.bank.service.ReadBankService
import org.springframework.stereotype.Service

@Service
class ReadBankCashTokenStateUseCase(
    private val readBankService: ReadBankService,
) {

    fun execute(compositeId: BankCashTokenStateMultiKeys): BankCashTokenStateDto {
        val result = readBankService.cashTokenStateById(compositeId)
        return BankCashTokenStateDto.toDto(result)
    }

}
