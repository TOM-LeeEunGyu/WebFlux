package io.medium.poc.api.controller.sec.request.query

import io.medium.poc.common.code.LocalDateForm
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.common.utils.stringToLocalDate
import io.medium.poc.domain.sec.model.entity.SecCashTokenTradingMultiKeys
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.time.LocalDate

@Schema(description = "증권사 캐시토큰 거래내역 조회 요청 객체")
data class SecCashTokenTradingQuery(
    @Schema(description = "은행기관번호")
    @field:NotNull(message = "은행기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "은행기관번호 정보는 필수입니다.")
    val bankInstNo: String,
    @Schema(description = "고객관리기관번호(증권사)")
    @field:NotNull(message = "고객관리기관번호(증권사) 정보는 필수입니다.")
    @field:NotBlank(message = "고객관리기관번호(증권사) 정보는 필수입니다.")
    val custMgmtInstNo: String,
    @Schema(description = "캐시토큰 ID")
    @field:NotNull(message = "캐시토큰 ID 정보는 필수입니다.")
    @field:NotBlank(message = "캐시토큰 ID 정보는 필수입니다.")
    val cashId: String,

    @Schema(description = "시작일자", example = "2023-07-07")
    @field:Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "날짜 형식은 yyyy-MM-dd 이어야 합니다.")
    val startTrdDt: String,
    @Schema(description = "종료일자", example = "2023-07-07")
    @field:Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "날짜 형식은 yyyy-MM-dd 이어야 합니다.")
    val endTrdDt: String,
) {

    fun compositeId(): SecCashTokenTradingMultiKeys {
        return SecCashTokenTradingMultiKeys(
            bankInstNo = bankInstNo,
            custMgmtInstNo = custMgmtInstNo,
            cashId = cashId,
            tradeDt = nowLocalDate(),
            tradeOrder = 0,
        )
    }

    fun start(): LocalDate {
        return stringToLocalDate(startTrdDt, LocalDateForm.YYYY_MM_DD.format())
    }

    fun end(): LocalDate {
        return stringToLocalDate(endTrdDt, LocalDateForm.YYYY_MM_DD.format())
    }

}
