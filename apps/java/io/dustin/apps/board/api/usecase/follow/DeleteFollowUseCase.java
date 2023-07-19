package io.dustin.apps.board.api.usecase.follow;

import io.dustin.apps.board.domain.follow.model.Follow;
import io.dustin.apps.board.domain.follow.model.dto.FollowDto;
import io.dustin.apps.board.domain.follow.service.WriteFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DeleteFollowUseCase {

    private final WriteFollowService writeFollowService;

    public FollowDto execute(Long followerId, Long followingId) {
        Follow follow = writeFollowService.getFollow(followerId, followingId);
        if(!follow.getFollowingId().equals(followingId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        writeFollowService.delete(followerId, followingId);
        FollowDto dto = FollowDto.from(follow);
        return FollowDto.from(follow);
    }

}
