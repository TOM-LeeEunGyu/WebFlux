package io.medium.poc.api.controller.com.model.request

import io.medium.poc.common.code.StoBaseAssetType
import io.medium.poc.common.code.TradeStartYn
import io.medium.poc.common.constraint.EnumCheck
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min

@Schema(description = "공통 STO 종목정보 목록 조회 요청 객체")
data class ComStoItemQuery(

    @Schema(description = "STO 종목번호")
    val stoItemNo: String?,

    @Schema(description = "STO 기초자산구분")
    @field:EnumCheck(enumClazz = StoBaseAssetType::class, permitNull = true, message = "STO 기초자산구분 필드는 01, 02 만 가능합니다.")
    val stoBaseAssetType: String?,

    @Schema(description = "매매가능여부")
    @field:EnumCheck(enumClazz = TradeStartYn::class, permitNull = true, message = "매매가능여부 필드는 Y, N 만 가능합니다.")
    val trdStartYn: String?,

    @field:Min(1, message = "가져올 검색 결과 수는 0보다 커야 합니다.")
    @Schema(description = "가져올 검색 결과 수, 기본 값은 10", example = "10")
    val recordsCount: Long = 10,

    @Schema(description = "다음 페이지 호출시 필요한 bookmark 정보, 없으면 비운다.")
    val bookmark: String? = null,
) {

    fun stoBaseAssetType(): StoBaseAssetType? {
        return stoBaseAssetType?.let { StoBaseAssetType.fromCode(it) }
    }

    fun trdStartYn(): TradeStartYn? {
        return trdStartYn?.let { TradeStartYn.fromCode(it) }
    }

}
