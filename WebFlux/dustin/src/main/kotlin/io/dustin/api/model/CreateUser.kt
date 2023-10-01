package io.dustin.api.model

import io.dustin.domain.user.model.code.Genre
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateUser(
    @field:NotNull
    @field:Size(min = 2, message = "유저 이름이 누락 되었습니다. 최소 한 글자 이상이어야 합니다.")
    val name: String,
    @field:NotNull(message = "장르 정보가 누락 되었습니다.")
    val genre: Genre?,
)