package io.medium.poc.domain.otc.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.OrderType
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 장외거래중개업자_토큰증권 호가조회_호가조회
 */
@Schema(description =  "장외거래중개업자_토큰증권 호가조회_호가조회")
data class OctPriceCheckDto(
    @Schema(description = "주문순번")
    val orderNo: Long?,

    @Schema(description = "STO 종목번호")
    val stoItemNo: String,

    @Schema(description = "STO종목명")
    val stoItemName: String,

    @Schema(description = "고객관리기관번호(hidden)")
    val custMgmtInstNo: String?,

    @Schema(description = "고객관리기관계좌번호(hidden)")
    val custMgmtInstAcntNo: String?,

    @JsonIgnore
    @Schema(hidden = true)
    val orderType: OrderType?,

    @Schema(description = "주문가격")
    val orderPrice: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "주문수량")
    val orderQty: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "주문일시")
    val orderDtm: LocalDateTime?,
) {
    @JsonProperty("orderTypeDesc")
    @Schema(description = "주문구분 섦명")
    fun orderTypeDesc() = orderType?.description

    @JsonProperty("orderTypeCode")
    @Schema(description = "주문구분 코드")
    fun orderTypeCode() = orderType?.code

}
