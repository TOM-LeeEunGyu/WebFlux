package io.dustin.apps.common.code//package io.dustin.apps.common.code
//
//import io.dustin.apps.board.domain.like.model.LikeCountService
//import io.dustin.apps.common.utils.CommonUtil
//import io.dustin.apps.common.utils.Supplier
//
//enum class BoardType(private val code: String, supplier: Supplier<LikeCountService>) {
//    POSTING("posting", Supplier<LikeCountService> { CommonUtil.getBean("posting", LikeCountService::class.java) }),
//    COMMENT("comment", Supplier<LikeCountService> { CommonUtil.getBean("comment", LikeCountService::class.java) }),
//    NOTICE("notice", Supplier<LikeCountService> { CommonUtil.getBean("notice", LikeCountService::class.java) });
//
//    private val supplier: Supplier<LikeCountService>
//
//    init {
//        this.supplier = supplier
//    }
//
//    val bean: LikeCountService
//        get() = supplier.get()
//}
