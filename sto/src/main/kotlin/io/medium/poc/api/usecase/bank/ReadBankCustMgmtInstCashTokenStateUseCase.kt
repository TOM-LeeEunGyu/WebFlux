package io.medium.poc.api.usecase.bank

import io.medium.poc.domain.bank.model.dto.BankCustMgmtInstCashTokenStateDto
import io.medium.poc.domain.bank.model.entity.BankCustMgmtInstCashTokenStateMultiKeys
import io.medium.poc.domain.bank.service.ReadBankService
import org.springframework.stereotype.Service

@Service
class ReadBankCustMgmtInstCashTokenStateUseCase(
    private val readBankService: ReadBankService,
) {

    fun execute(compositeId: BankCustMgmtInstCashTokenStateMultiKeys): BankCustMgmtInstCashTokenStateDto {
        val result = readBankService.custMgmtInstCashTokenStateById(compositeId)
        return BankCustMgmtInstCashTokenStateDto.toDto(result)
    }

}
