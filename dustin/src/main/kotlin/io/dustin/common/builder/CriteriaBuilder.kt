package io.dustin.common.builder

import io.dustin.common.exception.BadParameterException
import io.dustin.common.model.code.ConditionType
import io.dustin.common.model.request.WhereCondition
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Query.empty
import org.springframework.data.relational.core.query.Query.query
import org.springframework.util.MultiValueMap

/**
 * 코드 이해 안됨
 *
 * matrixVariable에서 "all" 키가 있는지 확인합니다. 만약 있으면 빈 Query를 반환합니다. 이는 "all" 키가 존재할 때 특별한 처리가 필요한 경우를 다루기 위한 것으로 보입니다.
 *
 * "all" 키가 없는 경우, matrixVariable을 순회하며 각 키와 값에 대한 처리를 수행합니다. 각 키와 값은 matrixVariable.map 함수를 사용하여 처리됩니다.
 *
 * 각 키와 값에 대한 처리는 다음과 같습니다:
 *
 * value[0]을 문자열로 변환한 후, ConditionType.of 함수를 사용하여 해당 코드를 가진 ConditionType을 찾습니다.
 * key와 value[1]을 사용하여 WhereCondition.from 함수를 호출하여 WhereCondition 객체를 생성합니다.
 * 위에서 얻은 ConditionType과 WhereCondition을 조합하여 해당 조건을 나타내는 Criteria 객체를 생성합니다. 이 작업은 ConditionType.of(value[0].toString()).create(WhereCondition.from(key, value[1])) 코드에서 수행됩니다.
 * 각 조건을 나타내는 Criteria 객체를 리스트로 모아서 Criteria.from(list)를 사용하여 하나의 Query 객체를 생성합니다.
 *
 * 만약 어떤 이유로든 조건을 생성하는 도중 예외가 발생하면, "누락된 정보가 있습니다. 확인하세요."라는 에러 메시지와 함께 BadParameterException을 발생시킵니다.
 *
 * 결과적으로, createQuery 함수는 주어진 matrixVariable을 기반으로 조건을 생성하고 이러한 조건들을 하나의 Query로 결합하여 반환합니다. 이 코드는 데이터베이스 쿼리 생성과 관련된 로직을 처리하는데 사용됩니다.
 */
fun createQuery(matrixVariable: MultiValueMap<String, Any>): Query {
    if(matrixVariable.containsKey("all")) {
        return empty()
    }
    val list = matrixVariable.map { (key, value) ->
        try {
            ConditionType.of(value[0].toString()).create(WhereCondition.from(key, value[1]))
        } catch(e: Exception) {
            throw BadParameterException("누락된 정보가 있습니다. 확인하세요.")
        }
    }
    return query(Criteria.from(list))
}