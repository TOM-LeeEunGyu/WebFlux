package io.dustin.apps.board.domain.like.service;

import io.dustin.apps.board.domain.like.model.Like;
import io.dustin.apps.board.domain.like.repository.LikeRepository;
import io.dustin.apps.common.code.BoardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.dustin.apps.common.utils.OptionalUtils.getEntity;

@RequiredArgsConstructor
@Service
public class ReadLIkeService {

    private final LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public Like getLike(Long userId, Long boardId, BoardType boardType) {
        return getEntity(this.likeRepository.findByUserIdAndBoardIdAndBoardType(userId, boardId, boardType), Like.class, "like not found");
    }

}
