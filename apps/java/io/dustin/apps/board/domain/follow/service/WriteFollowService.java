package io.dustin.apps.board.domain.follow.service;

import io.dustin.apps.board.domain.follow.model.Follow;
import io.dustin.apps.board.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WriteFollowService {
    private final FollowRepository followRepository;

    public Follow getFollow(Long followerId, Long followingId) {
        return this.followRepository.findByFollowerIdAndFollowingId(followerId,followingId);
    }

    public Follow create(Long followerId, Long followingId){
        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .build();
        System.out.println(follow);
        this.followRepository.save(follow);
        return follow;
    }

    @Transactional
    public void delete(Long followerId, Long followingId) {
        this.followRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);
    }



}
