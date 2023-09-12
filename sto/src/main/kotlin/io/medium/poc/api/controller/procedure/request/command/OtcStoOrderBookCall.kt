package io.medium.poc.api.controller.procedure.request.command

import io.medium.poc.common.code.OrderType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

@Schema(description = "토큰증권 호가등록 프로시져 요청 객체")
data class OtcStoOrderBookCall(

    @Schema(description = "고객관리기관번호")
    val custMgmtInstNo: String,

    @Schema(description = "고객관리기관계좌번호")
    val custMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호")
    val stoItemNo: String,

    @Schema(description = "주문구분")
    @field:EnumCheck(enumClazz = OrderType::class, message = "주문구분 필드는 01, 02만 가능합니다.")
    val orderType: String,

    @Schema(description = "주문수량")
    val orderQty: BigDecimal,

    @Schema(description = "주문가격")
    val orderPrice: BigDecimal,

    @Schema(description = "주문총액")
    val orderTotalAmt: BigDecimal,

    @Schema(description = "주문취소여부")
    @field:EnumCheck(enumClazz = YesOrNo::class, message = "주문취소여부 필드는 Y, N만 가능합니다.")
    val orderCancelYn: String,
)