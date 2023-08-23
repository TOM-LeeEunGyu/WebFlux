package io.dustin.apps.common.code

import io.dustin.apps.board.domain.like.model.LikeCountService
import io.dustin.apps.common.utils.getBean

enum class BoardType(
    val code: String,
    val supplier: () -> LikeCountService,
) {
    POSTING("posting", { getBean("posting", LikeCountService::class.java) }),
    COMMENT("comment", { getBean("comment", LikeCountService::class.java) });







}
