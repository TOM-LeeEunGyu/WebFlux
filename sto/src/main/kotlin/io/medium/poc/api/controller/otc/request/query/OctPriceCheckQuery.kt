package io.medium.poc.api.controller.otc.request.query

import com.fasterxml.jackson.annotation.JsonFormat
import io.medium.poc.common.code.OrderCheckYn
import io.medium.poc.common.code.OrderType
import io.medium.poc.common.code.TradingYn
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import java.time.LocalDate

@Schema(description = "장외거래중개업자_토큰증권 호가조회_호가조회 요청 객체")
data class OctPriceCheckQuery(

    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "주문일자", example = "1900-01-01")
    val orderDt: LocalDate?,

    @Schema(description = "주문구분(매도, 매수 각각 5호가 조회용)",example = "[01,02]")
    @field:EnumCheck(enumClazz = OrderType::class, permitNull = true, message = "OrderType 필드는 01,02 만 가능합니다.")
    val orderType: String?,

    @Schema(description = "주문가능확인여부",example = "Y")
    @field:EnumCheck(enumClazz = OrderCheckYn::class, permitNull = true, message = "OrderCheckYn 필드는 Y만 가능합니다.")
    val orderCkYn: String?,

    @Schema(description = "체결여부",example = "Y")
    @field:EnumCheck(enumClazz = TradingYn::class, permitNull = true, message = "TradingYn 필드는 Y만 가능합니다.")
    val tradingYn: String?,

    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보, 없으면 비운다.")
    val bookmark: Long? = null,

) {
    fun orderType(): OrderType? {
        return orderType?.let { OrderType.fromCode(it) }
    }

    fun orderCkYn(): OrderCheckYn? {
        return orderCkYn?.let { OrderCheckYn.fromCode(it) }
    }

    fun tradingYn(): TradingYn? {
        return tradingYn?.let { TradingYn.fromCode(it) }
    }
}
