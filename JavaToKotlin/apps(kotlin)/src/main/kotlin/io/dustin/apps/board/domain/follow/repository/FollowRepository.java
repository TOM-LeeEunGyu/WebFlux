package io.dustin.apps.board.domain.follow.repository;

import io.dustin.apps.board.domain.follow.model.Follow;
import io.dustin.apps.board.domain.follow.model.dto.FollowDto;
import io.dustin.apps.common.repository.BaseRepository;

import java.util.List;

public interface FollowRepository extends BaseRepository<Follow, Long> {

    Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);
    List<Follow> findByFollowingId(Long followingId);
    List<Follow> findByFollowerId(Long followerId);


}
