package io.medium.poc.domain.com.model.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.medium.poc.common.code.IncinerationYn
import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.TradeStartYn
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "투자자 정보 응답 객체")
data class ComStoItemDto(

    @Schema(description = "STO 종목번호")
    val stoItemNo: String,

    @Schema(description = "STO 종목명")
    val stoItemName: String?,

    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String?,

    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String?,

    @JsonIgnore
    @Schema(hidden = true)
    val stoBaseAssetType: StoBaseAssetType?,

    @JsonIgnore
    @Schema(hidden = true)
    val trdStartYn: TradeStartYn?,

    @JsonIgnore
    @Schema(hidden = true)
    val incinerationYn: IncinerationYn?,
) {

    @JsonProperty("stoBaseAssetTypeDesc")
    @Schema(description = "STO 기초자산구분 설명")
    val stoBaseAssetTypeDesc = stoBaseAssetType?.description

    @JsonProperty("stoBaseAssetTypeCode")
    @Schema(description = "STO 기초자산구분 코드")
    val stoBaseAssetTypeCode = stoBaseAssetType?.code


    @JsonProperty("trdStartYnDesc")
    @Schema(description = "매매가능여부 설명")
    val trdStartYnDesc = trdStartYn?.description

    @JsonProperty("trdStartYnCode")
    @Schema(description = "매매가능여부 코드")
    val trdStartYnCode = trdStartYn?.code


    @JsonProperty("incinerationYnDesc")
    @Schema(description = "소각여부 설명")
    val incinerationYnDesc = incinerationYn?.description

    @JsonProperty("incinerationYnCode")
    @Schema(description = "소각여부 코드")
    val incinerationYnCode = incinerationYn?.code
}
