package io.medium.poc.api.controller.bank.request.command

import com.fasterxml.jackson.annotation.JsonFormat
import io.medium.poc.common.code.CashTokenTradeType
import io.medium.poc.common.constraint.EnumCheck
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.domain.bank.model.entity.BankCashTokenTrading
import io.medium.poc.domain.bank.model.entity.BankCashTokenTradingMultiKeys
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDateTime

@Schema(description = "은행 캐시토큰 거래요청 요청 객체")
data class BankCashTokenTradingCommand(
    @Schema(description = "은행기관번호")
    @field:NotBlank(message = "은행기관번호 정보는 필수입니다.")
    @field:NotNull(message = "은행기관번호는 필수입니다.")
    @field:Size(max = 5, message = "은행기관번호의 길이가 5를 넘을 수 없습니다.")
    val bankInstNo: String,

    @Schema(description = "고객관리기관번호(증권사)")
    @field:NotBlank(message = "고객관리기관번호(증권사) 정보는 필수입니다.")
    @field:NotNull(message = "고객관리기관번호(증권사)는 필수입니다.")
    @field:Size(max = 5, message = "고객관리기관번호(증권사)의 길이가 5를 넘을 수 없습니다.")
    val custMgmtInstNo: String,

    @Schema(description = "캐시토큰 ID")
    @field:NotBlank(message = "캐시토큰 ID 정보는 필수입니다.")
    @field:NotNull(message = "캐시토큰 ID는 필수입니다.")
    @field:Size(max = 20, message = "캐시토큰 ID의 길이가 5를 넘을 수 없습니다.")
    val cashId: String,

    @Schema(description = "캐시토큰거래유형 코드")
    @field:EnumCheck(enumClazz = CashTokenTradeType::class, message = "CashTokenTradeType 필드는 01, 02 만 가능합니다.")
    val cashTokenTradeType: String,

    @Schema(description = "거래수량")
    @field:NotNull(message = "거래수량은 필수입니다.")
    @field:Min(1, message = "거래수량은 최소 0보다 커야 합니다.")
    val trdQty: BigDecimal,

    @Schema(description = "거래금액")
    @field:NotNull(message = "거래금액은 필수입니다.")
    @field:Min(1, message = "거래금액은 최소 0보다 커야 합니다.")
    val trdAmt: BigDecimal,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @field:NotNull(message = "거래일시는 필수입니다.")
    @Schema(type = "string", description = "거래일시", example = "1900-01-01 23:59:59")
    val trdDtm: LocalDateTime,
) {
    fun toCompositeId(): BankCashTokenTradingMultiKeys {
        return BankCashTokenTradingMultiKeys(
            bankInstNo = bankInstNo,
            custMgmtInstNo = custMgmtInstNo,
            cashId = cashId,
            tradeDt = nowLocalDate(),
            tradeOrder = 0,
        )
    }

    fun toEntity(tradeOrder: Long): BankCashTokenTrading {
        val compositeId = BankCashTokenTradingMultiKeys(
            bankInstNo = bankInstNo,
            custMgmtInstNo = custMgmtInstNo,
            cashId = cashId,
            tradeDt = nowLocalDate(),
            tradeOrder = tradeOrder,
        )
        return BankCashTokenTrading(
            compositeId = compositeId,
            cashTokenTradeType = CashTokenTradeType.fromCode(cashTokenTradeType),
            tradeQty = trdQty,
            tradeAmount = trdAmt,
            tradeDtm = trdDtm
        )
    }
}
