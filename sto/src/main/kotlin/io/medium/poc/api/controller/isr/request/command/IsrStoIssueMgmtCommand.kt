package io.medium.poc.api.controller.isr.request.command

import com.fasterxml.jackson.annotation.JsonFormat
import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.code.YesOrNo
import io.medium.poc.common.utils.nowLocalDate
import io.medium.poc.domain.isr.model.entity.IsrStoIssueMgmt
import io.medium.poc.domain.isr.model.entity.IsrStoIssueMgmtMultiKeys
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate

@Schema(description = "발행사 게재정보 등록 요청 객체")
data class IsrStoIssueMgmtCommand(

    @Schema(description = "발행관리기관번호(hidden)")
    @field:NotNull(message = "발행관리기관번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 5, message = "발행관리기관번호(hidden) 길이가 5를 넘을 수 없습니다.")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호(hidden)")
    @field:NotNull(message = "발행관리기관계좌번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "발행관리기관계좌번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 20, message = "발행관리기관계좌번호(hidden) 길이가 20를 넘을 수 없습니다.")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호(hidden)")
    @field:NotNull(message = "STO 종목번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "STO 종목번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 12, message = "STO종목번호(hidden) 길이가 12를 넘을 수 없습니다.")
    val stoItemNo: String,

    @Schema(description = "발행인번호(hidden)")
    @field:NotNull(message = "발행인번호(hidden) 정보는 필수입니다.")
    @field:NotBlank(message = "발행인번호(hidden) 정보는 필수입니다.")
    @field:Size(max = 10, message = "발행인번호(hidden) 길이가 10를 넘을 수 없습니다.")
    val issueNo: String,

    @Schema(description = "은행기관번호")
    @field:NotNull(message = "은행기관번호 정보는 필수입니다.")
    @field:NotBlank(message = "은행기관번호 정보는 필수입니다.")
    @field:Size(max = 5, message = "은행기관번호의 길이가 5를 넘을 수 없습니다.")
    val bankInstNo: String,

    @Schema(description = "은행계좌번호")
    @field:NotNull(message = "은행계좌번호 정보는 필수입니다.")
    @field:NotBlank(message = "은행계좌번호 정보는 필수입니다.")
    @field:Size(max = 20, message = "은행기관번호의 길이가 20를 넘을 수 없습니다.")
    val bankAcntNo: String,

    @Schema(description = "발행승인수량(토큰증권 발행량)")
    @field:NotNull(message = "발행승인수량(토큰증권 발행량) 정보는 필수입니다.")
    @field:Min(1, message = "발행승인수량(토큰증권 발행량)은 최소 0보다 커야 합니다.")
    val issueApprQty: BigDecimal,

    @Schema(description = "발행금액")
    @field:NotNull(message = "발행금액 정보는 필수입니다.")
    @field:Min(1, message = "발행금액은 최소 0보다 커야 합니다.")
    val issueAmt: BigDecimal,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "발행청약신청시작일자(발행청약시작일자)", example = "1900-01-01")
    val issueSbscrAplctStartDt: LocalDate,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "발행청약신청종료일자(발행청약종료일자)", example = "1900-01-01")
    val issueSbscrAplctEndDt: LocalDate,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "발행청약배정일자(청약배정일자)", example = "1900-01-01")
    val issueSbscrAssignDt: LocalDate,

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "발행일자", example = "1900-01-01")
    val issueDt: LocalDate,

    @Schema(description = "사업소개서자료")
    @field:NotNull(message = "사업소개서자료 정보는 필수입니다.")
    @field:NotBlank(message = "사업소개서자료 정보는 필수입니다.")
    val businessProfileData: String,

    @Schema(description = "투자설명서자료")
    @field:NotNull(message = "투자설명서자료 정보는 필수입니다.")
    @field:NotBlank(message = "투자설명서자료 정보는 필수입니다.")
    val prospectusData: String,

) {

    fun toCompositeId(): IsrStoIssueMgmtMultiKeys {
        return IsrStoIssueMgmtMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            stoItemNo = stoItemNo
        )
    }

    fun toIsrStoIssueMgmtEntity(): IsrStoIssueMgmt {
        val compositeId = IsrStoIssueMgmtMultiKeys(
            issueMgmtInstNo = issueMgmtInstNo,
            issueMgmtInstAcntNo = issueMgmtInstAcntNo,
            stoItemNo = stoItemNo
        )
        return IsrStoIssueMgmt(
            compositeId = compositeId,
            issueNo = issueNo,
            bankInstNo = bankInstNo,
            bankAcntNo = bankAcntNo,
            issueAplctDt = nowLocalDate(),
            issueApprQty = issueApprQty,
            issueAmt = issueAmt,
            issueSbscrAplctStartDt = issueSbscrAplctStartDt,
            issueSbscrAplctEndDt = issueSbscrAplctEndDt,
            issueSbscrAssignDt = issueSbscrAssignDt,
            issueDt = issueDt,
            businessProfileData = businessProfileData,
            prospectusData = prospectusData,
            issueStatusType = IssueStatusType.PUB_INFO_EVAL,
            sndYn = YesOrNo.Y,
            recYn = YesOrNo.N,
            apprYn = YesOrNo.N
        )
    }
}
