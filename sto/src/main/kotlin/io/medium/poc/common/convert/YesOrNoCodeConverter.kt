package io.medium.poc.common.convert

import io.medium.poc.common.code.YesOrNo
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class YesOrNoCodeConverter: AttributeConverter<YesOrNo, String> {

    override fun convertToDatabaseColumn(attribute: YesOrNo?): String {
        return attribute?.name ?: YesOrNo.N.name
    }

    override fun convertToEntityAttribute(dbData: String?): YesOrNo {
        return dbData?.let { YesOrNo.valueOf(it) } ?: YesOrNo.N
    }

}
