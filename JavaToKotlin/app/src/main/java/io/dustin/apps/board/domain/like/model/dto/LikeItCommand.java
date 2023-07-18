package io.dustin.apps.board.domain.like.model.dto;

import io.dustin.apps.common.code.BoardType;
import lombok.Getter;

@Getter
public class LikeItCommand {
    private Long id;
    private Long boardId;
    private Long userId;
    private BoardType boardType;
}
