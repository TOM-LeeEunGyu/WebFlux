package io.medium.poc.api.controller.invest.request.command

import io.medium.poc.common.code.LocalDateForm
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.common.utils.nowLocalDateTime
import io.medium.poc.domain.bank.model.entity.BankSbscrDeposit
import io.medium.poc.domain.bank.model.entity.BankSbscrDepositMultiKeys
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Schema(description = "투자자 청약금 입금처리 요청 객체")
data class InvestBankSbscrDepositCommand(
    @Schema(description = "고객관리기관번호")
    @field:NotBlank(message = "고객관리기관번호는 필수입니다.")
    @field:NotNull(message = "고객관리기관번호는 필수입니다.")
    @field:Size(max = 5, message = "고객관리기관번호의 길이가 5를 넘을 수 없습니다.")
    val custMgmtInstNo: String,

    @Schema(description = "고객관리기관계좌번호")
    @field:NotBlank(message = "고객관리기관계좌번호는 필수입니다.")
    @field:NotNull(message = "고객관리기관계좌번호는 필수입니다.")
    @field:Size(max = 20, message = "고객관리기관계좌번호의 길이가 20을 넘을 수 없습니다.")
    val custMgmtInstAcntNo: String,

    @Schema(description = "입금액")
    @field:NotNull(message = "입금액은 필수입니다.")
    @field:Min(1, message = "입금액은 최소 0보다 커야 합니다.")
    val depositAmt: BigDecimal,
) {
    fun toEntity(): BankSbscrDeposit {
        val compositeId = BankSbscrDepositMultiKeys(
            depositDt = LocalDateForm.YYYY_MM_DD_NO_DELIMITER.transform(nowLocalDate()),
            custMgmtInstNo = custMgmtInstNo,
            custMgmtInstAcntNo = custMgmtInstAcntNo,
        )
        return BankSbscrDeposit(
            compositeId = compositeId,
            depositAmount = depositAmt,
            tradeDtm = nowLocalDateTime(),
            sndYn = YesOrNo.N,
        )
    }
}
