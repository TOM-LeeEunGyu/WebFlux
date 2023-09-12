package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 주문구분 코드
 */
@Schema(enumAsRef = true)
enum class OrderType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    SELL("매수", "01"),
    BUY("매도", "02");

    override fun code() = code

    companion object {
        fun fromCode(code: String): OrderType = OrderType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<OrderType>(OrderType::class.java)

}
