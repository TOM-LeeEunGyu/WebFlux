package io.dustin.apps.board.domain.like.repository;

import io.dustin.apps.board.domain.like.model.Like;
import io.dustin.apps.common.code.BoardType;
import io.dustin.apps.common.repository.BaseRepository;

import java.util.Optional;

public interface LikeRepository extends BaseRepository<Like, Long> {

    void deleteByUserIdAndBoardIdAndBoardType(Long userId, Long boardId, BoardType boardType);
    Optional<Like> findByUserIdAndBoardIdAndBoardType(Long userId, Long boardId, BoardType boardType);
}
