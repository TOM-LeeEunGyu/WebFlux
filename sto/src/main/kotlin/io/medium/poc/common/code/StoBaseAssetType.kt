package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * STO 기초자산구분 코드
 */
@Schema(enumAsRef = true)
enum class StoBaseAssetType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    INVEST_CONTRACT_SEC("투자계약증권", "01"),
    NON_TRUST_PROFIT_CERT("비금전 신탁수익증권", "02");

    override fun code() = code

    companion object {
        fun fromCode(code: String): StoBaseAssetType = StoBaseAssetType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<StoBaseAssetType>(StoBaseAssetType::class.java)

}
