package io.dustin.apps.board.domain.like.model.dto

import io.dustin.apps.common.code.BoardType

class LikeItCommand (

    val id: Long,
    val boardId: Long,
    val userId: Long,
    val boardType: BoardType,

)


