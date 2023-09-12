package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 거래유형 코드
 */
@Schema(enumAsRef = true)
enum class TradeType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    INITIAL_REGISTRATION("최초등록", "01"),
    ALTERNATIVE("대체", "02"),
    TRADING("매매", "03"),
    ESTABLISHMENT_PLEDGE("질권설정", "04"),
    CANCELLATION_PLEDGE("질권말소", "05"),
    ESTABLISHMENT_ATTACHMENT("압류설정", "06"),
    CANCELLATION_TRADING("압류말소", "07");

    override fun code() = code

    companion object {
        fun fromCode(code: String): TradeType = TradeType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<TradeType>(TradeType::class.java)

}
