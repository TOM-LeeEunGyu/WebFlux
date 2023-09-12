package io.medium.poc.domain.otc.service

import io.medium.poc.common.code.OrderCheckYn
import io.medium.poc.common.code.OrderType
import io.medium.poc.common.code.TradingYn
import io.medium.poc.common.utils.notFoundEntity
import io.medium.poc.domain.otc.model.dto.OctEnforcementResultDto
import io.medium.poc.domain.otc.model.dto.OctPriceCheckDto
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBook
import io.medium.poc.domain.otc.repository.OtcStoOrderBookRepository
import io.medium.poc.domain.otc.repository.OtcStoTradingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ReadOtcService(
    private val otcStoOrderBookRepository: OtcStoOrderBookRepository,
    private val otcStoTradingRepository: OtcStoTradingRepository,
) {

    /** 장외거래중개업자_토큰증권 호가등록_호가 조회 */
    @Transactional(readOnly = true)
    fun getPriceCheckList(
        stoItemNo: String?,
        orderDt: LocalDate?,
        orderType: OrderType?,
        orderCkYn: OrderCheckYn?,
        tradingYn: TradingYn?,
        recordsCount: Long,
        bookmark:Long?
    ): List<OctPriceCheckDto>{
        return otcStoOrderBookRepository.getPriceCheckList(
            stoItemNo,
            orderDt,
            orderType,
            orderCkYn,
            tradingYn,
            recordsCount,
            bookmark
        )
    }

    /** 장외거래중개업자_토큰증권 호가등록_호가등록시 순번 조회 */
    fun otcStoOrderBookNo(): Long {
        return otcStoOrderBookRepository.otcStoOrderBookNo()
    }


    /** 장외거래중개업자_토큰증권체결내역_체결결과조회 */
    @Transactional(readOnly = true)
    fun getEnforcementResulList(
            stoItemNo:String?,
            custMgmtInstNo:String,
            custMgmtInstAcntNo:String,
            recordsCount: Long,
            bookmark: Long?
    ):List<OctEnforcementResultDto> {
        return otcStoTradingRepository.enforcementResult(
                stoItemNo,
                custMgmtInstNo,
                custMgmtInstAcntNo,
                recordsCount,
                bookmark
        )
    }

    /** otcStoTrading 데이터 베이스 순번 조회 */
    @Transactional(readOnly = true)
    fun otcStoTradingNo(): Long {
        return otcStoTradingRepository.otcStoTradingNo()
    }

    @Transactional(readOnly = true)
    fun getOtcStoOrderBookByOrderNo(orderNo: Long): OtcStoOrderBook {
        val errorMessage = "주문 순번[$orderNo]로 조회된 정보가 없습니다."
        return otcStoOrderBookRepository.getOtcStoOrderBookByOrderNo(orderNo) ?: notFoundEntity(errorMessage)
    }

}
