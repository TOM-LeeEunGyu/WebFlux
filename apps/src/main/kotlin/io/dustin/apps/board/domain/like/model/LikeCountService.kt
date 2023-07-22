package io.dustin.apps.board.domain.like.model

interface LikeCountService {
    fun likeCount(id: Long)
    fun likeUnCount(id: Long)
}
