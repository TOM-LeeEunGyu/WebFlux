package io.medium.poc.api.controller.otc.request.command

import io.medium.poc.common.code.OrderType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.constraint.EnumCheck
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.common.utils.nowLocalDateTime
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBook
import io.medium.poc.domain.otc.model.entity.OtcStoOrderBookMultiKeys
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Schema(description = "장외거래중개업자 토큰증권 호가등록 요청 객체")
data class OtcStoOrderBookCommand(
    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호 정보는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호 정보는 필수입니다.")
    @field:Size(max = 12, message = "STO 종목번호 길이가 12를 넘을 수 없습니다.")
    val stoItemNo: String,

    @Schema(description = "고객관리기관번호(hidden)")
    @field:NotNull(message = "고객관리기관번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 5, message = "고객관리기관번호(hidden) 길이가 5를 넘을 수 없습니다.")
    val custMgmtInstNo: String,

    @Schema(description = "고객관리기관계좌번호(hidden)")
    @field:NotNull(message = "고객관리기관계좌번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관계좌번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 20, message = "고객관리기관계좌번호(hidden) 길이가 20를 넘을 수 없습니다.")
    val custMgmtInstAcntNo: String,

    @Schema(description = "주문구분", example = "[01,02]")
    @field:EnumCheck(enumClazz = OrderType::class, message = "OrderType 필드는 01, 02 만 가능합니다.")
    val orderType: String,

    @Schema(description = "주문가격")
    @field:NotNull(message = "주문가격 정보는 필수입니다.")
    @field:Min(1, message = "주문가격는 1보다 커야 합니다.")
    val orderPrice: BigDecimal,

    @Schema(description = "주문수량")
    @field:NotNull(message = "주문수량 정보는 필수입니다.")
    @field:Min(1, message = "주문수량는 0보다 커야 합니다.")
    val orderQty: BigDecimal,

    @Schema(description = "주문취소여부")
    @field:EnumCheck(enumClazz = YesOrNo::class, message = "orderCancelYn 필드는 Y, N 만 가능합니다.")
    val orderCancelYn: String = YesOrNo.N.name,

) {
    fun toOtcStoOrderBookEntity(orderNo:Long): OtcStoOrderBook {
        val compositeId = OtcStoOrderBookMultiKeys(
            orderDt = nowLocalDate(),
            stoItemNo = stoItemNo,
            orderNo = orderNo,
        )
        return OtcStoOrderBook(
            compositeId = compositeId,
            custMgmtInstNo = custMgmtInstNo,
            custMgmtInstAcntNo = custMgmtInstAcntNo,
            orderType = OrderType.fromCode(orderType),
            orderPrice = orderPrice,
            orderQty = orderQty,
            orderTotalAmt = orderPrice.multiply(orderQty),
            orderDtm = nowLocalDateTime(),
            orderCancelYn = YesOrNo.N
        )
    }

    fun orderTotalAmt(): BigDecimal {
        return orderPrice.multiply(orderQty)
    }

}
