package io.medium.poc.domain.bank.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.bank.model.entity.BankSbscrDeposit
import io.medium.poc.domain.bank.model.entity.BankSbscrDepositMultiKeys

/**
 * 은행 청약금액입금내역 repository
 */
interface BankSbscrDepositRepository: BaseRepository<BankSbscrDeposit, BankSbscrDepositMultiKeys>