package io.dustin.common.model.code

import io.dustin.common.model.request.WhereCondition
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.isEqual

/**
 * matrixvariable을 사용하기위한 enum
 */
enum class ConditionType(
    val code: String,
    private val criteria: (WhereCondition) -> Criteria
) {
    LTE("lte", { where(it.column).lessThanOrEquals(it.value)}),
    LT("lt", { where(it.column).lessThan(it.value)}),
    GTE("gte", { where(it.column).greaterThanOrEquals(it.value)}),
    GT("gt", { where(it.column).greaterThan(it.value)}),
    EQ("eq", { where(it.column).isEqual(it.value)}),
    LIKE("like", { where(it.column).like("%${it.value}%")});

    fun create(condition: WhereCondition): Criteria {
        return criteria(condition)
    }

    companion object {
        /**
         * null이면 illegalArgumentException을 던지고 있지만 ETC를 던져도 상관없다.
         * @param code
         * @return ConditionType
         *
         * code 파라미터: 찾고자 하는 ConditionType 상수의 코드 문자열을 나타냅니다.
         * values() 함수를 사용하여 ConditionType enum 클래스의 모든 상수를 배열로 가져옵니다.
         * firstOrNull 함수를 사용하여 배열에서 주어진 조건을 만족하는 첫 번째 상수를 찾습니다. 여기서 조건은 conditionType.code가 code 파라미터와 같은지 (대소문자 무시) 확인하는 것입니다.
         * 만약 조건을 만족하는 상수가 없을 경우, ?: 연산자를 사용하여 기본값으로 EQ 상수를 반환합니다. 이는 주어진 코드 문자열에 해당하는 조건이 없을 때 사용할 기본 조건을 정의하는 것입니다.
         */
        fun of(code: String): ConditionType = values().firstOrNull { conditionType-> conditionType.code.equals(code, ignoreCase = true) }
            ?: EQ
    }

}