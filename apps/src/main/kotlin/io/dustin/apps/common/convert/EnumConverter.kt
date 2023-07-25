package io.dustin.apps.common.convert

import jakarta.persistence.*

/**
 * 추상 클래스를 상속받을 수 있는 클래스는 T: Enum<T> -> enum 만 상속받을 수 있다.
 *
 * 생성자는 Class<T> 타입의 매개변수 clazz를 받는다. -> enum 타입에 대해 동작하기 위해선 enum 타입 클래스의 정보가 필요하다.
 * clazz는 생성자 내부에서 private val로 선언되어 있으므로, 해당 클래스 내부에서는 clazz 변수를 자유롭게 사용할 수 있다. <- 무슨말인지 이해 안됨.
 * AttributeConverter 가 엔티티와 db 필드사이에 변환을 처리한다.
 */

@Converter
abstract class EnumConverter<T: Enum<T>> protected constructor(private val clazz: Class<T>) :
    AttributeConverter<T, String> where T: InterfaceGenericEnum<out T> {

    override fun convertToDatabaseColumn(attribute: T): String {
        var attribute: T? = attribute
        if (attribute == null) {
            val enums = clazz.enumConstants
            attribute = enums.first{ en -> en == en.defaultIfNull() }
                ?: throw IllegalArgumentException("해당 컬럼의 db enum값과 해당 enum클래스의 정보가 맞지 않습니다. 확인하세요.")
        }
        return attribute.name
    }


    override fun convertToEntityAttribute(dbData: String): T {
        val enums = clazz.enumConstants
        return try {
            enums.first{ en -> en.name == dbData }
                ?: throw IllegalArgumentException("해당 컬럼의 db enum값과 해당 enum클래스의 정보가 맞지 않습니다. 확인하세요.")
        } catch (e: NullPointerException) {
            defaultEnum(enums)
        }
    }


    private fun defaultEnum(enums: Array<T>): T {
        return enums.first{ en -> en == en.defaultIfNull() }
    }
}