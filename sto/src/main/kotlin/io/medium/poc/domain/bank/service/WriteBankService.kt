package io.medium.poc.domain.bank.service

import io.medium.poc.domain.bank.model.entity.BankCashTokenTrading
import io.medium.poc.domain.bank.model.entity.BankSbscrDeposit
import io.medium.poc.domain.bank.repository.BankCashTokenTradingRepository
import io.medium.poc.domain.bank.repository.BankSbscrDepositRepository
import org.springframework.stereotype.Service

@Service
class WriteBankService(
    private val bankCashTokenTradingRepository: BankCashTokenTradingRepository,
    private val bankSbscrDepositRepository: BankSbscrDepositRepository,
) {

    fun saveCashTokenTrading(entity: BankCashTokenTrading) {
        bankCashTokenTradingRepository.save(entity)
    }

    fun saveBankSbscrDeposit(entity: BankSbscrDeposit) {
        bankSbscrDepositRepository.save(entity)
    }

}
