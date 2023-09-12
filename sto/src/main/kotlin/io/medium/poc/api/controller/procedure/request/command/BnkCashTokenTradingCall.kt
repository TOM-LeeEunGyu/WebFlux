package io.medium.poc.api.controller.procedure.request.command

import io.medium.poc.common.code.CashTokenTradeType
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "캐시토큰 거래 생성 및 캐시토큰 State 변경 처리 프로시져 요청 객체")
data class BnkCashTokenTradingCall(
    @Schema(description = "은행기관번호")
    val bankInstNo: String,
    @Schema(description = "고객관리기관번호")
    val custMgmtInstNo: String,
    @Schema(description = "캐시토큰 ID")
    val cashId: String,
    @Schema(description = "캐시토큰거래유형", example = "[01, 02, 03, 04]")
    @field:EnumCheck(enumClazz = CashTokenTradeType::class, message = "입력값(승인여부) 필드는 01, 02, 03, 04만 가능합니다.")
    val cashTokenTrdType: String,
    @Schema(description = "거래수량")
    val trdQty: BigDecimal,
    @Schema(description = "거래금액")
    val trdAmt: BigDecimal,
) {
    fun cashTokenTrdType(): CashTokenTradeType {
        return CashTokenTradeType.fromCode(cashTokenTrdType)
    }
}
