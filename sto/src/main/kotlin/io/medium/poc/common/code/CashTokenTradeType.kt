package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 캐시토큰거래유형 코드
 */
@Schema(enumAsRef = true)
enum class CashTokenTradeType(
    val description: String,
    val code: String,
): InterfaceGenericEnum  {

    BUY("구매", "01"),
    REFUND("환불", "02"),
    SELL("판매", "03"),
    COLLECT("회수", "04");

    override fun code() = code

    companion object {
        fun fromCode(code: String): CashTokenTradeType = values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<CashTokenTradeType>(CashTokenTradeType::class.java)

}
