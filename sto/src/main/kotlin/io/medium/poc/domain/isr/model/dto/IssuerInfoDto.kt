package io.medium.poc.domain.isr.model.dto

import io.medium.poc.domain.isr.model.entity.IsrStoIssue
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "발행사 게재정보접수_발행인정보조회 응답 객체")
data class IssuerInfoDto (
    @Schema(description = "발행인명")
    val issueName: String?,

    @Schema(description = "발행인주소")
    val issueAddr: String?,

    @Schema(description = "발행인이메일주소")
    val issueEmail: String?,

    @Schema(description = "발행인대표자")
    val issueRprsnName: String?,

    @Schema(description = "발행인홈페이지")
    val issueHomepage: String?,

    @Schema(description = "발행인연락처")
    val issueContactpnt: String?,

    @Schema(description = "발행인사업자등록번호")
    val issueBusiRegNo: String?,

    @Schema(description = "발행인법인등록번호")
    val issueCorpRegNo: String?,

    @Schema(description = "발행인거래기관번호")
    val issueTrdInstNo: String?,

    @Schema(description = "발행인거래기관계좌번호")
    val issueTrdAcntNo: String?,
) {

    companion object{
        fun toDto(entity: IsrStoIssue) = with(entity) {
            IssuerInfoDto(
                issueName = issueName,
                issueAddr = issueAddr,
                issueEmail = issueEmail,
                issueRprsnName = issueRprsnName,
                issueHomepage = issueHomepage,
                issueContactpnt = issueContactpnt,
                issueBusiRegNo = issueBusiRegNo,
                issueCorpRegNo = issueCorpRegNo,
                issueTrdInstNo = issueTrdInstNo,
                issueTrdAcntNo = issueTrdAcntNo,
            )
        }
    }

}
