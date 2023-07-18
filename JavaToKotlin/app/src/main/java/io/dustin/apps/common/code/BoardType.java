package io.dustin.apps.common.code;

import io.dustin.apps.board.domain.like.model.LikeCountService;
import io.dustin.apps.common.utils.CommonUtil;
import io.dustin.apps.common.utils.Supplier;

public enum BoardType {

    POSTING("posting", () -> CommonUtil.getBean("posting", LikeCountService.class)),
    COMMENT("comment", () -> CommonUtil.getBean("comment", LikeCountService.class)),
    NOTICE("notice", () -> CommonUtil.getBean("notice", LikeCountService.class));



    private String code;

    private Supplier<LikeCountService> supplier;

    BoardType(String code, Supplier<LikeCountService> supplier) {
        this.code = code;
        this.supplier = supplier;
    }

    public LikeCountService getBean() {
        return this.supplier.get();
    }

}
