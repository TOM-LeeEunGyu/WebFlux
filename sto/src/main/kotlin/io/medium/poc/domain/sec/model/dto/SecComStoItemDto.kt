package io.medium.poc.domain.sec.model.dto

import io.medium.poc.common.code.IncinerationYn
import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.TradeStartYn
import io.medium.poc.domain.com.model.entity.ComStoItem
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

/**
 * 증권사 토큰소각실행 dto
 */
@Schema(description = "증권사 토큰소각실행 응답 객체")
data class SecComStoItemDto(
    @Schema(description = "STO 종목번호")
    val stoItemNo: String,

    @Schema(description = "STO 종목명")
    val stoItemName: String? = null,

    @Schema(description = "STO 기초자산구분")
    val stoBaseAssetType: StoBaseAssetType? = null,

    @Schema(description = "매매가능여부")
    var tradeStartYn: TradeStartYn? = null,

    @Schema(description = "소각여부")
    var incinerationYn: IncinerationYn? = null,

    @Schema(description = "발행관리기관번호")
    val issueMgmtInstNo: String? = null,

    @Schema(description = "발행관리기관계좌번호")
    val issueMgmtInstAcntNo: String? = null,

    ) {
   companion object {
       fun toDto(entity: ComStoItem) = with(entity) {
           SecComStoItemDto(
               stoItemNo = stoItemNo,
               stoItemName = stoItemName,
               stoBaseAssetType = stoBaseAssetType,
               tradeStartYn = tradeStartYn,
               incinerationYn = incinerationYn,
               issueMgmtInstNo = issueMgmtInstNo,
               issueMgmtInstAcntNo = issueMgmtInstAcntNo

           )
       }
   }

}