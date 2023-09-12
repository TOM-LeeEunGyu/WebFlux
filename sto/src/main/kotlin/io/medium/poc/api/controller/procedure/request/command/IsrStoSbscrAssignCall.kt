package io.medium.poc.api.controller.procedure.request.command

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "청약배정처리 프로시져 요청 객체")
data class IsrStoSbscrAssignCall(
    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String,

    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String,

    @Schema(description = "STO 종목번호")
    val stoItemNo: String,
)