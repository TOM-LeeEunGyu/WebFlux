package io.medium.poc.api.controller.procedure.request.command

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "공모신청승인 프로시져 요청 객체")
data class KstStoIssueAplctCall(
    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "이슈발행번호")
    val issueNo: String,
)