package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 잔고유형 코드
 */
@Schema(enumAsRef = true)
enum class BalanceType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    NORMAL("일반", "01"),
    ESTABLISHMENT_PLEDGE("질권설정", "02"),
    ATTACHMENT("압류", "03"),
    OWNER_PROOF("소유자증명", "04"),
    CASH("캐시", "05");

    override fun code() = code

    companion object {
        fun fromCode(code: String): BalanceType = BalanceType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<BalanceType>(BalanceType::class.java)

}
