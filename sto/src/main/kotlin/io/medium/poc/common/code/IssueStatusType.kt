package io.medium.poc.common.code

import io.medium.poc.common.convert.EnumCodeConverter
import io.medium.poc.common.convert.InterfaceGenericEnum
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 발행상태구분 코드
 */
@Schema(enumAsRef = true)
enum class IssueStatusType(
    val description: String,
    val code: String,
): InterfaceGenericEnum {

    UNDER_REV_APP("공모 심사", "01"),
    SUB_PUB_INFO("게재정보 등록", "02"),
    PUB_INFO_EVAL("게재정보 심사", "03"),
    PUB_REJECT("게재정보 거절", "04"),
    ONGOING_SUB("청약신청 중", "05"),
    SUB_CLOSED("청약신청 종료", "06"),
    OFFER_APPROVAL("청약승인", "07"),
    ASSIGN_APPROVAL("배정승인", "08"),
    IN_PROGRESS("거래중", "09"),
    INCINERATION("소각", "10");

    override fun code() = code

    companion object {
        fun fromCode(code: String): IssueStatusType = IssueStatusType.values().first { it.code == code }
    }

    class CodeConverter: EnumCodeConverter<IssueStatusType>(IssueStatusType::class.java)

}
