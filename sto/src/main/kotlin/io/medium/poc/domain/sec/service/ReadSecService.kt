package io.medium.poc.domain.sec.service

import io.medium.poc.common.extensions.findByIdOrThrow
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.com.model.entity.ComStoItem
import io.medium.poc.domain.sec.model.dto.AssetInfoDto
import io.medium.poc.domain.sec.model.entity.*
import io.medium.poc.domain.sec.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate

@Service
class ReadSecService(
    private val secCustMgmtInstCashTokenStateRepository: SecCustMgmtInstCashTokenStateRepository,
    private val secCashTokenTradingRepository: SecCashTokenTradingRepository,
    private val secAccountValidateRepository: SecAccountValidateRepository,
    private val secCustAccountRepository: SecCustAccountRepository,
    private val secComStoItemRepository: SecComStoItemRepository,
) {

    /**
     * 증권사 고객관리기관 캐시토큰 State 조회
     */
    @Transactional(readOnly = true)
    fun custMgmtInstCashTokenStateById(
        compositeId: SecCustMgmtInstCashTokenStateMultiKeys
    ): SecCustMgmtInstCashTokenState {
        val errorMessage = with(compositeId) {
            "고객관리기관번호(증권사)[$custMgmtInstNo], 캐시토큰 ID[$cashId], 잔고유형[${balanceType.code}]로 조회된 정보가 없습니다."
        }
        return secCustMgmtInstCashTokenStateRepository.findByIdOrThrow(compositeId, errorMessage)
    }

    /**
     * 순번 조회
     */
    @Transactional(readOnly = true)
    fun retrieveTradeOrderNumber(): Long {
        return secCashTokenTradingRepository.retrieveTradeOrderNumber()
    }

    /**
     * 캐시토큰 거래내역 조회
     */
    @Transactional(readOnly = true)
    fun cashTokenTradingList(
        compositeId: SecCashTokenTradingMultiKeys,
        startTradeDt: LocalDate,
        endTradeDt: LocalDate
    ): List<SecCashTokenTrading> {
        return secCashTokenTradingRepository.cashTokenTradingList(compositeId, startTradeDt, endTradeDt)
    }

    /**
     * 위탁계좌 조회전 확인
     */
    @Transactional(readOnly = true)
    fun verifyCustMgmtInstAcntNo(custMgmtInstNo: String, custMgmtInstAcntNo: String): SecAccountValidate {
        return secAccountValidateRepository.findByMgmtInstNoAndMgmtInstAcntNo(custMgmtInstNo, custMgmtInstAcntNo)
            ?: notFoundEntity("고객관리기관번호[$custMgmtInstNo], 고객관리계좌번호[$custMgmtInstAcntNo]으로 조회된 정보가 없습니다.")

    }

    /**
     * 고객 보유자산 정보 조회
     */
    @Transactional(readOnly = true)
    fun getAssetInformation(
        custMgmtInstNo: String,
        custMgmtInstAcntNo: String,
        recordsCount: Long,
        bookmark:Long
    ): List<AssetInfoDto> {
        return secCustAccountRepository.getAssetInformation(custMgmtInstNo,custMgmtInstAcntNo,recordsCount,bookmark)
    }

    /**
     * 소각승인 여부 변경을 위한 조회 메서드
     */
    @Transactional(readOnly = true)
    fun findByStoItemNo(stoItemNo: String): ComStoItem {
        return secComStoItemRepository.findByStoItemNo(stoItemNo)
            ?: notFoundEntity("STO 종목번호[$stoItemNo] 으로 조회된 정보가 없습니다.")
    }

    /**
     * 토큰증권관리(소각실행) 조회
     */
    @Transactional(readOnly = true)
    fun balanceQtyQuery(stoItemNo: String, custMgmtInstNo: String): BigDecimal? {
        return secComStoItemRepository.balanceQtyQuery(stoItemNo, custMgmtInstNo)
    }

    /**
     * 위탁계좌유효성확인 조회 리스트
     */
    @Transactional(readOnly = true)
    fun getSecAccountValidateList(reqDt: LocalDate, recordsCount: Long, bookmark: Long?): List<SecAccountValidate> {
        return secCustAccountRepository.getSecAccountValidateList(reqDt, recordsCount, bookmark)
    }

}
