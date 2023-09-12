package io.medium.poc.domain.isr.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.IssueStatusType
import io.medium.poc.common.code.YesOrNo
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDate

/**
 * 발행사 토큰증권목록조회 리스트 응답 객체
 */
@Schema(description = "발행사 토큰증권목록조회 리스트 응답 객체")
data class IsrStoIssueAplctDto(
    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String?,
    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String?,
    @Schema(description = "발행인번호")
    val issueNo: String?,
    @Schema(description = "발행상품이름")
    val stoItemName: String?,
    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,
    @Schema(description = "발행신청수량")
    val issueProposalQty: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "발행승인수량")
    val issueApproveQty: BigDecimal? = BigDecimal.ZERO,
    @Schema(description = "발행인상호명(발행인명)")
    val issueName: String?,
    @Schema(description = "발행신청일자", example = "1900-01-01")
    val issueAplctDt: LocalDate?,
    @Schema(type = "string", description = "최종발행일자", example = "1900-01-01")
    val lstIssueDt: LocalDate?,
    @JsonIgnore
    @Schema(hidden = true)
    val issueStateType: IssueStatusType?,

    @Schema(description = "수신여부")
    val recYn: YesOrNo?,
    @Schema(description = "승인여부")
    val apprYn: YesOrNo?,
    @Schema(description = "송신여부")
    val sndYn: YesOrNo?,
) {

    @JsonProperty("issueStateTypeDesc")
    @Schema(description = "현황 설명")
    fun issueStateTypeDesc(): String {
        return issueStateType?.description ?: checkIssueStateTypeDesc()
    }

    @JsonProperty("issueStateTypeCode")
    @Schema(description = "현황 코드")
    fun issueStateTypeCode(): String {
        return issueStateType?.code ?: checkIssueStateTypeCode()
    }

    @JsonIgnore
    @Schema(hidden = true)
    private fun checkIssueStateTypeDesc(): String {
        return if(recYn == YesOrNo.Y && apprYn == YesOrNo.Y) {
            IssueStatusType.SUB_PUB_INFO.description
        } else {
            IssueStatusType.UNDER_REV_APP.description
        }
    }

    @JsonIgnore
    @Schema(hidden = true)
    private fun checkIssueStateTypeCode(): String {
        return if(recYn == YesOrNo.Y && apprYn == YesOrNo.Y) {
            IssueStatusType.SUB_PUB_INFO.code
        } else {
            IssueStatusType.UNDER_REV_APP.code
        }
    }

}
