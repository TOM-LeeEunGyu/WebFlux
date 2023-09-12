package io.medium.poc.api.controller.procedure.request.command

import io.medium.poc.common.code.TradeType
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Schema(description = "토큰증권 체결처리 프로시져 요청 객체")
data class OtcStoTradingCall(

    @Schema(description = "STO 종목번호")
    @field:NotNull(message = "STO 종목번호 정보는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호 정보는 필수입니다.")
    @field:Size(max = 12, message = "STO 종목번호 길이가 12를 넘을 수 없습니다.")
    val stoItemNo: String,

    @Schema(description = "매도고객관리기관번호")
    @field:NotNull(message = "매도고객관리기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "매도고객관리기관번호 정보는 필수입니다.")
    @field:Size(max = 5, message = "고객관리기관번호 길이가 5를 넘을 수 없습니다.")
    val selCustMgmtInstNo: String,

    @Schema(description = "매도고객관리기관계좌번호")
    @field:NotNull(message = "매도고객관리기관계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "매도고객관리기관계좌번호 정보는 필수입니다.")
    @field:Size(max = 20, message = "매도고객관리기관계좌번호 길이가 20를 넘을 수 없습니다.")
    val selCustMgmtInstAcntNo: String,

    @Schema(description = "매수고객관리기관번호")
    @field:NotNull(message = "매수고객관리기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "매수고객관리기관번호 정보는 필수입니다.")
    @field:Size(max = 5, message = "매수고객관리기관번호 길이가 5를 넘을 수 없습니다.")
    val buyCustMgmtInstNo: String,

    @Schema(description = "매수고객관리기관계좌번호")
    @field:NotNull(message = "매수고객관리기관계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "매수고객관리기관계좌번호 정보는 필수입니다.")
    @field:Size(max = 20, message = "매수고객관리기관계좌번호 길이가 20를 넘을 수 없습니다.")
    val buyCustMgmtInstAcntNo: String,

    @Schema(description = "거래유형", example = "[01, 02, 03, 04, 05, 06, 07]")
    @field:EnumCheck(enumClazz = TradeType::class, message = "TradeType 필드는 01, 02, 03, 04, 05, 06, 07만 가능합니다.")
    val trdType: String,

    @Schema(description = "거래수량")
    @field:NotNull(message = "거래수량 정보는 필수입니다.")
    @field:Min(1, message = "거래수량은 최소 0보다 커야 합니다.")
    val trdQty: BigDecimal,

    @Schema(description = "거래금액")
    @field:NotNull(message = "거래금액 정보는 필수입니다.")
    @field:Min(1, message = "거래금액은 최소 0보다 커야 합니다.")
    val trdAmt: BigDecimal,

    @Schema(description = "주문순번(오더북)")
    @field:NotNull(message = "주문순번(오더북)는 필수입니다.")
    val orderNo: Long,
)