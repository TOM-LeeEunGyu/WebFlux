package io.dustin.api.router.mugi.model

import io.dustin.common.constraint.EnumCheck
import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.model.code.ReleasedType
import isParamBlankThrow
import org.springframework.data.relational.core.sql.SqlIdentifier

data class UpdateMugi(
    val name: String? = null,
    val label: String? = null,
    @field:EnumCheck(enumClazz = ReleasedType::class, permitNull = true, message = "releasedType 필드는 JAK, GUMAE만 가능합니다.")
    val releasedType: String? = null,
    val releasedYear: Int? = null,
    val format: String? = null,
) {
    fun createAssignments(record: Mugi): Pair<Mugi, MutableMap<SqlIdentifier, Any>> {
        val assignments = mutableMapOf<SqlIdentifier, Any>()
        name?.let {
            isParamBlankThrow(it)
            assignments[SqlIdentifier.unquoted("name")] = it
            record.name = it
        }
        label?.let {
            isParamBlankThrow(it)
            assignments[SqlIdentifier.unquoted("label")] = it
            record.label = it
        }
        releasedType?.let {
            assignments[SqlIdentifier.unquoted("releasedType")] = it
            record.releasedType = ReleasedType.valueOf(it.uppercase())
        }
        releasedYear?.let {
            assignments[SqlIdentifier.unquoted("releasedYear")] = it
            record.releasedYear = it
        }
        format?.let {
            isParamBlankThrow(it)
            assignments[SqlIdentifier.unquoted("format")] = it
            record.format = it
        }
        return record to assignments
    }
}