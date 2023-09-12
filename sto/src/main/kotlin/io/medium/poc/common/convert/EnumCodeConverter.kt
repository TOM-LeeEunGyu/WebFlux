package io.medium.poc.common.convert

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
abstract class EnumCodeConverter<T: Enum<T>> protected constructor(
    private val clazz: Class<T>
): AttributeConverter<T, String> where T: InterfaceGenericEnum {

    /** enum 값을 가져와서 코드 정보를 가지고 와 db 에 저장 */
    override fun convertToDatabaseColumn(attribute: T?): String? {
        return attribute?.code()
    }

    /** db 값을 가져와서 코드 정보로 enum 으로 반환 */
    override fun convertToEntityAttribute(dbData: String?): T? {
        val enums = clazz.enumConstants
        return dbData?.let { enums.first{ en -> en.code() == it } }
    }

}
