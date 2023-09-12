package io.medium.poc.api.controller.com.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 코드 목록 응답 객체
 */
@Schema(description = "코드 목록 리스트 응답 객체")
data class CodeDto(

    @JsonProperty("SND_YN")
    @Schema(description = "송신여부")
    val sendYn: List<CodeInfoDto>,
    @JsonProperty("REC_YN")
    @Schema(description = "수신여부")
    val receiveYn: List<CodeInfoDto>,
    @JsonProperty("APPR_YN")
    @Schema(description = "승인여부")
    val approveYn: List<CodeInfoDto>,
    @JsonProperty("ACNT_VALIDATE_YN")
    @Schema(description = "계좌유효성확인여부")
    val accountValidateYn: List<CodeInfoDto>,
    @JsonProperty("SBSCR_MARGIN_AMT_PAY_YN")
    @Schema(description = "청약증거금납입확인여부")
    val subscriptionMarginAmountPayYn: List<CodeInfoDto>,
    @JsonProperty("ORDER_CK_YN")
    @Schema(description = "주문가능확인여부")
    val orderCheckYn: List<CodeInfoDto>,
    @JsonProperty("TRADING_YN")
    @Schema(description = "체결여부")
    val tradingYn: List<CodeInfoDto>,
    @JsonProperty("ISSUE_STATUS_TYPE")
    @Schema(description = "발행상태구분")
    val issueStatusType: List<CodeInfoDto>,
    @JsonProperty("STO_BASE_ASSET_TYPE")
    @Schema(description = "STO 기초자산구분")
    val stoBaseAssetType: List<CodeInfoDto>,
    @JsonProperty("INVST_ID_TYPE")
    @Schema(description = "투자자식별정보구분")
    val investorIdType: List<CodeInfoDto>,
    @JsonProperty("STO_RIGHT_TYPE")
    @Schema(description = "STO 권리구분")
    val stoRightType: List<CodeInfoDto>,
    @JsonProperty("TRD_TYPE")
    @Schema(description = "거래유형")
    val tradeType: List<CodeInfoDto>,
    @JsonProperty("BALANCE_TYPE")
    @Schema(description = "잔고유형")
    val balanceType: List<CodeInfoDto>,
    @JsonProperty("ORDER_TYPE")
    @Schema(description = "주문구분")
    val orderType: List<CodeInfoDto>,
    @JsonProperty("CASH_TOKEN_TRD_TYPE")
    @Schema(description = "캐시토큰거래유형")
    val cashTokenTradeType: List<CodeInfoDto>,
    @JsonProperty("INCINERATION_REASON_TYPE")
    @Schema(description = "소각사유구분")
    val incinerationReasonType: List<CodeInfoDto>,
    @JsonProperty("INCINERATION_YN")
    @Schema(description = "소각여부")
    val incinerationYn: List<CodeInfoDto>,
    @JsonProperty("TRD_START_YN")
    @Schema(description = "매매가능여부")
    val tradeStartYn: List<CodeInfoDto>,
)