package io.medium.poc.domain.sec.repository.custom

import java.math.BigDecimal

interface CustomSecComStoItemRepository {
    fun balanceQtyQuery(stoItemNo: String, custMgmtInstNo: String): BigDecimal?
}
