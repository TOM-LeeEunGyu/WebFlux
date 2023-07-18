package io.dustin.apps.board.api.usecase.follow;

import io.dustin.apps.board.domain.follow.model.Follow;
import io.dustin.apps.board.domain.follow.model.dto.FollowDto;
import io.dustin.apps.board.domain.follow.service.WriteFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteFollowUseCase {

    private final WriteFollowService writeFollowService;

    public FollowDto execute(Long followerId, Long followingId) {
        Follow follow = writeFollowService.create(followerId, followingId);
        FollowDto dto = FollowDto.from(follow);
        return FollowDto.from(follow);
    }
}

