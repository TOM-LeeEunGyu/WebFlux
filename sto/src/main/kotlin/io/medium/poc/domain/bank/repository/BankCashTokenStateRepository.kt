package io.medium.poc.domain.bank.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.bank.model.entity.BankCashTokenState
import io.medium.poc.domain.bank.model.entity.BankCashTokenStateMultiKeys

/**
 * 은행 캐시토큰 State repository
 */
interface BankCashTokenStateRepository: BaseRepository<BankCashTokenState, BankCashTokenStateMultiKeys>