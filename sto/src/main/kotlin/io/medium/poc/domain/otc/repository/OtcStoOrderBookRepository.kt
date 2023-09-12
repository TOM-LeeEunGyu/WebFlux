package io.medium.poc.domain.otc.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBook
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBookMultiKeys
import io.medium.poc.domain.otc.repository.custom.CustomAssetInfoRepository
import io.medium.poc.domain.otc.repository.custom.CustomOtcStoOrderBookRepository

interface OtcStoOrderBookRepository
    : BaseRepository<OtcStoOrderBook, OtcStoOrderBookMultiKeys>, CustomOtcStoOrderBookRepository, CustomAssetInfoRepository