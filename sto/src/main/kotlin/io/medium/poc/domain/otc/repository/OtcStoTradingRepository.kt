package io.medium.poc.domain.otc.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.otc.model.entity.OtcStoTrading
import io.medium.poc.domain.otc.model.entity.OtcStoTradingMultiKeys
import io.medium.poc.domain.otc.repository.custom.CustomOtcStoTradingRepository

interface OtcStoTradingRepository: BaseRepository<OtcStoTrading, OtcStoTradingMultiKeys>, CustomOtcStoTradingRepository