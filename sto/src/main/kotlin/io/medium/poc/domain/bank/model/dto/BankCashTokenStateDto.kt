package io.medium.poc.domain.bank.model.dto

import io.medium.poc.domain.bank.model.entity.BankCashTokenState
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 은행 캐시토큰 State Dto
 */
@Schema(description = "은행 캐시토큰 상태 조회 응답 객체")
data class BankCashTokenStateDto(
    @Schema(description = "직전수량")
    val postQty: BigDecimal,
    @Schema(description = "현재수량")
    val currentQty: BigDecimal,
    @Schema(description = "증감수량")
    val inOutQty: BigDecimal,
) {
    companion object {
        fun toDto(entity: BankCashTokenState) = with(entity) {
            BankCashTokenStateDto(
                postQty = postQty!!,
                currentQty = currentQty!!,
                inOutQty = inOutQty!!,
            )
        }
    }
}
