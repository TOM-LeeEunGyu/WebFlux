package io.dustin.api.router.user.model

import io.dustin.domain.user.model.User
import io.dustin.domain.user.model.code.Job
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateUser(
    @field:NotNull
    @field:Size(min = 2, message = "유저 이름이 누락 되었습니다. 최소 한 글자 이상이어야 합니다.")
    val name: String,
    @field:NotNull(message = "장르 정보가 누락 되었습니다.")
    val job: String,
) {
    fun toEntity(): User {
        return User(name = name, job = Job.valueOf(job.uppercase()))
    }
}