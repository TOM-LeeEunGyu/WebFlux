package io.medium.poc.common.constraint

import io.medium.poc.common.convert.InterfaceGenericEnum
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EnumValueValidator: ConstraintValidator<EnumCheck, String?> {

    private lateinit var annotation: EnumCheck

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        return value?.let { valueString ->
            val enumValues = this.annotation.enumClazz.java.enumConstants
            val checkedEnum = enumValues.firstOrNull { enum ->
                when(enum) {
                    is InterfaceGenericEnum -> enum.code() == valueString
                    else -> enum.name.equals(valueString, ignoreCase = true)
                }
            }
            checkedEnum != null
        } ?: checked()
    }

    override fun initialize(constraintAnnotation: EnumCheck) {
        this.annotation = constraintAnnotation
    }

    private fun checked(): Boolean {
        if(annotation.permitNull) {
            return true
        }
        return false
    }

}
