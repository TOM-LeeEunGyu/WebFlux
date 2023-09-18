package io.dustin.apps.board.domain.member.model.dto

data class JwtClaim(
    val memberId: Long,
    val email: String,
)