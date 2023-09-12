package io.medium.poc.api.controller.invest.request.command

import io.medium.poc.common.code.InvestorIdType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.constraint.EnumCheck
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAplct
import io.medium.poc.domain.isr.model.entity.IsrStoSbscrAplctAplctMultiKeys
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Schema(description = "투자자 청약신청 등록 요청 객체")
data class InvestStoSbscrAplctCommand(

    @Schema(description = "발행관리기관번호")
    @field:NotBlank(message = "발행관리기관번호는 필수입니다.")
    @field:NotNull(message = "발행관리기관번호는 필수입니다.")
    @field:Size(max = 5, message = "발행관리기관번호의 길이가 5를 넘을 수 없습니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    @field:NotBlank(message = "발행관리기관번호는 필수입니다.")
    @field:NotNull(message = "발행관리기관번호는 필수입니다.")
    @field:Size(max = 20, message = "발행관리기관번호의 길이가 20을 넘을 수 없습니다.")
    val issueMgmtInstAcntNo: String,

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

    @Schema(description = "STO 종목번호")
    @field:NotBlank(message = "STO 종목번호는 필수입니다.")
    @field:NotNull(message = "STO 종목번호는 필수입니다.")
    @field:Size(max = 12, message = "STO 종목번호의 길이가 12를 넘을 수 없습니다.")
    val stoItemNo: String,

    @Schema(description = "청약자명")
    @field:NotBlank(message = "청약자명은 필수입니다.")
    @field:NotNull(message = "청약자명은 필수입니다.")
    @field:Size(max = 50, message = "청약자명의 길이가 50을 넘을 수 없습니다.")
    val sbscrName: String,

    @Schema(description = "투자자식별정보")
    @field:NotBlank(message = "투자자식별정보은 필수입니다.")
    @field:NotNull(message = "투자자식별정보은 필수입니다.")
    @field:Size(max = 13, message = "투자자식별정보의 길이가 13을 넘을 수 없습니다.")
    val invstId: String,

    @Schema(description = "투자자식별정보구분")
    @field:EnumCheck(enumClazz = InvestorIdType::class, message = "투자자식별정보구분 필드는 01, 02 만 가능합니다.")
    val invstIdType: String,

    @Schema(description = "발행청약신청수량")
    @field:NotNull(message = "발행청약신청수량은 필수입니다.")
    @field:Min(1, message = "발행청약신청수량은 최소 0보다 커야 합니다.")
    val issueSbscrAplctQty: BigDecimal,

    @Schema(description = "발행청약증거금")
    @field:NotNull(message = "발행청약증거금은 필수입니다.")
    @field:Min(1, message = "발행청약증거금은 최소 0보다 커야 합니다.")
    val issueSbscrMarginAmt: BigDecimal,

) {
    fun toEntity(): IsrStoSbscrAplct {
        val compositeId = IsrStoSbscrAplctAplctMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            custMgmtInstNo = custMgmtInstNo,
            custMgmtInstAcntNo = custMgmtInstAcntNo,
            stoItemNo = stoItemNo,
        )
        return IsrStoSbscrAplct(
            compositeId = compositeId,
            sbscrName = sbscrName,
            invstId = invstId,
            investorIdType = InvestorIdType.fromCode(invstIdType),
            issueSbscrAplctQty = issueSbscrAplctQty,
            issueSbscrMarginAmt = issueSbscrMarginAmt,
            issueSbscrAplctDt = nowLocalDate(),
            sndYn = YesOrNo.N,
        )
    }
}
