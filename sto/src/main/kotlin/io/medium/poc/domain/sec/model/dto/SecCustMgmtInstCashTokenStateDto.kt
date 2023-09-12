package io.medium.poc.domain.sec.model.dto

import io.medium.poc.domain.sec.model.entity.SecCustMgmtInstCashTokenState
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 증권사 고객관리기관 캐시토큰 State Dto
 */
@Schema(description = "증권사 고객관리기관 캐시토큰 상태 조회 응답 객체")
class SecCustMgmtInstCashTokenStateDto(
    @Schema(description = "직전수량")
    val postQty: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "현재수량")
    val currentQty: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "증감수량")
    val inOutQty: BigDecimal? = BigDecimal.ZERO,
) {
    companion object {
        fun toDto(entity: SecCustMgmtInstCashTokenState) = with(entity) {
            SecCustMgmtInstCashTokenStateDto(
                postQty = postQty,
                currentQty = currentQty,
                inOutQty = inOutQty,
            )
        }
    }
}