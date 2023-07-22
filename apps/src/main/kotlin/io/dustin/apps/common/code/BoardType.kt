package io.dustin.apps.common.code//package io.dustin.apps.common.code

import io.dustin.apps.board.domain.like.model.LikeCountService

enum class BoardType(
    val code: String,
    val supplier: () -> LikeCountService,
) {
    POSTING("posting", { CommonUtil.getBean("posting", LikeCountService::class.java) }),
    COMMENT("comment", { CommonUtil.getBean("comment", LikeCountService::class.java) }),
    NOTICE("notice", { CommonUtil.getBean("notice", LikeCountService::class.java) });
}
