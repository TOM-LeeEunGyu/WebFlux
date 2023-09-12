package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 투자자식별정보구분 코드
 */
@Schema(enumAsRef = true)
enum class InvestorIdType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    ID("주민등록번호", "01"),
    BUZ_NUM("법인등록번호", "02");

    override fun code() = code

    companion object {
        fun fromCode(code: String): InvestorIdType = InvestorIdType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<InvestorIdType>(InvestorIdType::class.java)

}
