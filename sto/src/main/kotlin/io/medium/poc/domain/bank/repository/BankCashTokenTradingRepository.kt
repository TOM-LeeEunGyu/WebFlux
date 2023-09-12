package io.medium.poc.domain.bank.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.bank.model.entity.BankCashTokenTrading
import io.medium.poc.domain.bank.model.entity.BankCashTokenTradingMultiKeys
import io.medium.poc.domain.bank.repository.custom.CustomBankCashTokenTradingRepository

/**
 * 은행 캐시토큰거래 repository
 */
interface BankCashTokenTradingRepository
    : BaseRepository<BankCashTokenTrading, BankCashTokenTradingMultiKeys>, CustomBankCashTokenTradingRepository