package io.medium.poc.domain.bank.service

import io.medium.poc.common.extensions.findByIdOrThrow
import io.medium.poc.domain.bank.model.dto.BankSettlementIssueInfoDto
import io.medium.poc.domain.bank.model.dto.BankStoIssueAplctDto
import io.medium.poc.domain.bank.model.entity.*
import io.medium.poc.domain.bank.repository.BankCashTokenStateRepository
import io.medium.poc.domain.bank.repository.BankCashTokenTradingRepository
import io.medium.poc.domain.bank.repository.BankCustMgmtInstCashTokenStateRepository
import io.medium.poc.domain.bank.repository.BankRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ReadBankService(
    private val bankCashTokenStateRepository: BankCashTokenStateRepository,
    private val bankCashTokenTradingRepository: BankCashTokenTradingRepository,
    private val bankCustMgmtInstCashTokenStateRepository: BankCustMgmtInstCashTokenStateRepository,
    private val bankRepository: BankRepository,
) {

    /**
     * 캐시토큰 상태 조회
     */
    @Transactional(readOnly = true)
    fun cashTokenStateById(compositeId: BankCashTokenStateMultiKeys): BankCashTokenState {
        val errorMessage= with(compositeId) {
            "은행기관번호[$bankInstNo], 캐시토큰 ID[$cashId], 잔고유형[${balanceType.code}]로 조회된 정보가 없습니다."
        }
        return bankCashTokenStateRepository.findByIdOrThrow(compositeId, errorMessage)
    }

    /**
     * 고객관리기관 캐시토큰 상태 조회
     */
    @Transactional(readOnly = true)
    fun custMgmtInstCashTokenStateById(compositeId: BankCustMgmtInstCashTokenStateMultiKeys): BankCustMgmtInstCashTokenState {
        val errorMessage = with(compositeId) {
            "고객관리기관번호[$custMgmtInstNo], 캐시토큰 ID[$cashId], 잔고유형[${balanceType.code}]로 조회된 정보가 없습니다."
        }
        return bankCustMgmtInstCashTokenStateRepository.findByIdOrThrow(compositeId, errorMessage)
    }

    @Transactional(readOnly = true)
    fun retrieveTradeOrderNumber(
        bankInstNo: String,
        custMgmtInstNo: String,
        cashId: String,
        tradeDt: LocalDate
    ): Long {
        return bankCashTokenTradingRepository.retrieveTradeOrderNumber(bankInstNo, custMgmtInstNo, cashId, tradeDt)
    }

    /**
     * 캐시토큰 거래내역 조회
     */
    @Transactional(readOnly = true)
    fun cashTokenTradingList(
        compositeId: BankCashTokenTradingMultiKeys,
        startTradeDt: LocalDate,
        endTradeDt: LocalDate
    ): List<BankCashTokenTrading> {
        return bankCashTokenTradingRepository.cashTokenTradingList(compositeId, startTradeDt, endTradeDt)
    }

    /**
     * 은행 토큰증권 목록조회
     */
    @Transactional(readOnly = true)
    fun bankStoIssueApplications(
        search: String,
        recordsCount: Long,
        bookmark: String?
    ): List<BankStoIssueAplctDto> {
        return bankRepository.bankStoIssueApplications(search, recordsCount, bookmark)
    }

    /**
     * 은행 발행대금 정산처리 조회
     */
    @Transactional(readOnly = true)
    fun bankSettlementIssueInformation(stoItemNo: String): BankSettlementIssueInfoDto {
        return bankRepository.bankSettlementIssueInformation(stoItemNo)
    }

}
