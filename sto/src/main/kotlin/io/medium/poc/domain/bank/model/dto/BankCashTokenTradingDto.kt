package io.medium.poc.domain.bank.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.CashTokenTradeType
import io.medium.poc.domain.bank.model.entity.BankCashTokenTrading
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 은행 캐시토큰거래 dto
 */
@Schema(description = "은행 캐시토큰 거래내역 조회 응답 객체")
data class BankCashTokenTradingDto(
    @Schema(description = "고객관리기관번호(증권사)")
    val custMgmtInstNo: String,
    @Schema(description = "캐시토큰 ID")
    val cashId: String,
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "거래일자", example = "2023-07-07")
    val trdDt: LocalDate,
    @Schema(description = "거래순번")
    val trdNo: Long,
    @JsonIgnore
    @Schema(hidden = true)
    val cashTokenTradeType: CashTokenTradeType,
    @Schema(description = "거래수량")
    val trdQty: BigDecimal,
    @Schema(description = "거래금액")
    val trdAmt: BigDecimal,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(type = "string", description = "거래일시", example = "1900-01-01 23:59:59")
    val trdDtm: LocalDateTime,
) {

    @JsonProperty("cashTokenTradeTypeDesc")
    @Schema(description = "캐시토큰거래유형 설명")
    val cashTokenTradeTypeDesc = cashTokenTradeType.description

    @JsonProperty("cashTokenTradeTypeCode")
    @Schema(description = "캐시토큰거래유형 코드")
    val cashTokenTradeTypeCode = cashTokenTradeType.code

    companion object {
        fun toDto(entity: BankCashTokenTrading) = with(entity) {
            BankCashTokenTradingDto(
                custMgmtInstNo = compositeId.custMgmtInstNo,
                cashId = compositeId.cashId,
                cashTokenTradeType = cashTokenTradeType!!,
                trdDt = compositeId.tradeDt,
                trdNo = compositeId.tradeOrder,
                trdQty = tradeQty!!,
                trdAmt = tradeAmount!!,
                trdDtm = tradeDtm!!,
            )
        }
    }

}
