package io.medium.poc.api.usecase.bank

import io.medium.poc.domain.bank.model.dto.BankCashTokenTradingDto
import io.medium.poc.domain.bank.model.entity.BankCashTokenTradingMultiKeys
import io.medium.poc.domain.bank.service.ReadBankService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReadBankCashTokenTradingUseCase(
    private val readBankService: ReadBankService,
) {

    fun execute(compositeId: BankCashTokenTradingMultiKeys,
                startTradeDt: LocalDate,
                endTradeDt: LocalDate
    ): List<BankCashTokenTradingDto> {
        return readBankService.cashTokenTradingList(compositeId, startTradeDt, endTradeDt)
                              .map { entity -> BankCashTokenTradingDto.toDto(entity) }
    }

}
