package io.medium.poc.domain.bank.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.bank.model.entity.BankCustMgmtInstCashTokenState
import io.medium.poc.domain.bank.model.entity.BankCustMgmtInstCashTokenStateMultiKeys

/**
 * 은행 고객관리기관 캐시토큰 State repository
 */
interface BankCustMgmtInstCashTokenStateRepository
    : BaseRepository<BankCustMgmtInstCashTokenState, BankCustMgmtInstCashTokenStateMultiKeys>