package io.medium.poc.domain.sec.service

import io.medium.poc.common.code.AccountValidateYn
import io.medium.poc.common.code.IncinerationYn
import io.medium.poc.domain.com.model.entity.ComStoItem
import io.medium.poc.domain.sec.model.entity.SecAccountValidate
import io.medium.poc.domain.sec.model.entity.SecCashTokenTrading
import io.medium.poc.domain.sec.repository.SecAccountValidateRepository
import io.medium.poc.domain.sec.repository.SecCashTokenTradingRepository
import io.medium.poc.domain.sec.repository.SecComStoItemRepository
import org.springframework.stereotype.Service

@Service
class WriteSecService(
    private val secCashTokenTradingRepository: SecCashTokenTradingRepository,
    private val secAccountValidateRepository: SecAccountValidateRepository,
    private val secComStoItemRepository: SecComStoItemRepository,
) {

    fun saveCashTokenTrading(entity: SecCashTokenTrading)  {
        secCashTokenTradingRepository.save(entity)
    }

    fun verifyCustMgmtInstAcntNo(entity: SecAccountValidate){
        entity.acntValidateYn = AccountValidateYn.CHECKED
        secAccountValidateRepository.save(entity)
    }

    /** 소각승인 여부를 변경해준다 */
    fun updateIncineration(entity: ComStoItem) {
        entity.incinerationYn = IncinerationYn.INCINERATION
        secComStoItemRepository.save(entity)

    }

}
