package io.medium.poc.domain.sec.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.sec.model.entity.SecCashTokenTrading
import io.medium.poc.domain.sec.model.entity.SecCashTokenTradingMultiKeys
import io.medium.poc.domain.sec.repository.custom.CustomSecCashTokenTradingRepository

/**
 * 증권사 캐시토큰거래내역 repository
 */
interface SecCashTokenTradingRepository
    : BaseRepository<SecCashTokenTrading, SecCashTokenTradingMultiKeys>, CustomSecCashTokenTradingRepository