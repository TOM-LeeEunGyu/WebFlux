package io.dustin.apps.board.api.usecase.like;

import io.dustin.apps.board.domain.like.model.Like;
import io.dustin.apps.board.domain.like.model.dto.LikeDto;
import io.dustin.apps.board.domain.like.service.WriteLikeService;
import io.dustin.apps.common.code.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WriteLikeUseCase {

    private final WriteLikeService writeLikeService;

    @Transactional
    public LikeDto execute(Long userId, Long boardId,  BoardType boardType) {
        Like like = writeLikeService.create(userId, boardId, boardType);
        LikeDto dto = LikeDto.from(like);
        return LikeDto.from(like);
    }
}
