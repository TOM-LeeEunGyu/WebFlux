package io.dustin.api.model

import io.dustin.common.exception.BadParameterException
import io.dustin.domain.user.model.User
import io.dustin.domain.user.model.code.Genre
import org.springframework.data.relational.core.sql.SqlIdentifier

data class UpdateUser(
    val name: String?,
    val genre: Genre?,
) {
    /**
     * 데이터 베이스에 정보가 있으면 MutableMap에 값을 담는다.(정확히 이해 못함)
     */
    fun createAssignments(user: User): Pair<User, MutableMap<SqlIdentifier, Any>> {
        val assignments = mutableMapOf<SqlIdentifier, Any>()
        name?.let {
            assignments[SqlIdentifier.unquoted("name")] = it
            user.name = it
        }
        genre?.let {
            assignments[SqlIdentifier.unquoted("genre")] = it
            user.genre = it
        }
        if(assignments.isEmpty()) {
            throw BadParameterException("업데이트 정보가 누락되었습니다. [name, genre] 정보를 확인하세요.")
        }
        return user to assignments
    }
}