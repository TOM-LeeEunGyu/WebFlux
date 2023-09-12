package io.medium.poc.domain.otc.service

import io.medium.poc.common.code.TradingYn
import io.medium.poc.domain.com.model.entity.ComInvestor
import io.medium.poc.domain.com.repository.ComInvestorRepository
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBook
import io.medium.poc.domain.otc.model.entity.OtcStoTrading
import io.medium.poc.domain.otc.repository.OtcStoOrderBookRepository
import io.medium.poc.domain.otc.repository.OtcStoTradingRepository
import org.springframework.stereotype.Service

@Service
class WriteOtcService(
    private val otcStoOrderBookRepository: OtcStoOrderBookRepository,
    private val otcStoTradingRepository: OtcStoTradingRepository,
    private val comInvestorRepository: ComInvestorRepository,
) {
    /**
     * 장외거래중개업자_토큰증권 호가등록_호가등록
     */
    fun saveOtcStoOrderBook(entity: OtcStoOrderBook): OtcStoOrderBook {
        return otcStoOrderBookRepository.save(entity)
    }

    /**
     * 장외거래중개업자_토큰증권 체결처리_체결처리시 업데이트
     */
    fun updateOtcStoOrderBook(entity: OtcStoOrderBook): OtcStoOrderBook {
        entity.tradingYn = TradingYn.TRADED
        return otcStoOrderBookRepository.save(entity)
    }


    fun saveOtcStoTrading(entity: OtcStoTrading): OtcStoTrading {
        return otcStoTradingRepository.save(entity)
    }

    /**
     * 사용할 수도 있어서 만들어 놓음
     */
    fun saveComInvestor(entity: ComInvestor): ComInvestor {
        return comInvestorRepository.save(entity)
    }

}
