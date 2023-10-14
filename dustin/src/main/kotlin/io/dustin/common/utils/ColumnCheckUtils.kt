package io.dustin.common.utils

import io.dustin.common.exception.BadParameterException
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.util.ParsingUtils.reconcatenateCamelCase
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

/**
 * getNativeColumn 함수는 주어진 클래스 clazz의 필드 및 해당 필드에 대한 @Column 주석을 조사하여 주어진 columnName과 일치하는 네이티브 컬럼 이름을 가져온다?
 */
fun getNativeColumn(columnName: String, clazz: KClass<*>): String {
    val members = clazz.java.declaredFields
    val annotationValues = members.mapNotNull { member ->
        val list = member.annotations
        val column = list.find { it.annotationClass == Column::class } as? Column
        column?.value?.let { it }
    }

    val fieldValues = clazz.memberProperties.mapNotNull { it.name }
    return if (annotationValues.contains(columnName)) {
        columnName
    } else if (fieldValues.contains(columnName)) {
        toSnakeCaseByUnderscore(columnName)
    } else {
        throw BadParameterException("${columnName}이 존재하지 않습니다.")
    }
}


fun toSnakeCaseByUnderscore(source: String): String {
    return reconcatenateCamelCase(source, "_")
}