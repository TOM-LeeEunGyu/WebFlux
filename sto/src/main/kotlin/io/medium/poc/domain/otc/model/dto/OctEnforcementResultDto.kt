package io.medium.poc.domain.otc.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.TradeType
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 장외거래중개업자_토큰증권체결내역_체결결과조회
 */
@Schema(description = "장외거래중개업자_토큰증권체결내역_체결결과조회")
data class OctEnforcementResultDto(

    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @Schema(description = "STO 종목명")
    val stoItemName: String?,

    @Schema(hidden = true)
    val trdType: TradeType?,

    @Schema(description = "거래수량")
    val trdQty: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "거래금액")
    val trdAmt: BigDecimal? = BigDecimal.ZERO,

    @Schema(description = "거래일시")
    val trdDtm: LocalDateTime?,
) {

    @JsonProperty("trdTypeDesc")
    @Schema(description = "거래유형 섦명")
    fun trdTypeDesc() = trdType?.description

    @JsonProperty("trdTypeCode")
    @Schema(description = "거래유형 코드")
    fun trdTypeCode() = trdType?.code

}
