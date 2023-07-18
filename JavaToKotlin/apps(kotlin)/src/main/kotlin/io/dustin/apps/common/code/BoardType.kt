package io.dustin.apps.common.code

import io.dustin.apps.board.domain.like.model.LikeCountService

enum class BoardType(private val code: String?, supplier: Supplier<LikeCountService?>?) {
    POSTING("posting", Supplier<LikeCountService?> { CommonUtil.getBean("posting", LikeCountService::class.java) }),
    COMMENT("comment", Supplier<LikeCountService?> { CommonUtil.getBean("comment", LikeCountService::class.java) }),
    NOTICE("notice", Supplier<LikeCountService?> { CommonUtil.getBean("notice", LikeCountService::class.java) });

    private val supplier: Supplier<LikeCountService?>?

    init {
        this.supplier = supplier
    }

    fun getBean(): LikeCountService? {
        return supplier.get()
    }
}
