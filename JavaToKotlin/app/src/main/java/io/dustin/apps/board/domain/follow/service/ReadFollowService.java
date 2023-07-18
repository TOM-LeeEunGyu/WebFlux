package io.dustin.apps.board.domain.follow.service;

import io.dustin.apps.board.domain.follow.model.Follow;
import io.dustin.apps.board.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReadFollowService {

    private final FollowRepository followRepository;

    public List<Follow> getFollowingIdList(Long followerId) {return followRepository.findByFollowerId(followerId);}
    public List<Long> followingIds(Long followerId) {return getFollowingIdList(followerId).stream().map(Follow::getFollowingId).collect(Collectors.toList());}

    public List<Follow> getFollowerIdList(Long followingId) {return followRepository.findByFollowingId(followingId);}
    public List<Long> followerIds(Long followingId) {return getFollowerIdList(followingId).stream().map(Follow::getFollowerId).collect(Collectors.toList());}





}
