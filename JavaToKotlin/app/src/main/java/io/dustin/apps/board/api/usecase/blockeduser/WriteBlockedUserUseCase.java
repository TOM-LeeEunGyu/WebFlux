package io.dustin.apps.board.api.usecase.blockeduser;

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser;
import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto;
import io.dustin.apps.board.domain.blockeduser.service.WriteBlockedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteBlockedUserUseCase {

    private final WriteBlockedUserService writeBlockedUserService;

    /**
     유저를 차단하면
     1. 차단한 유저의 게시물이 보이면 안된다 -- > 이건 querydsl로 해결한다.
     2. 만약 서로 팔로우 상태중이라면(맞팔) 끊어져야 한다 -- > 유스케이스에 추가해야할듯
     * */

    /** 유저와 차단한 상대 팔로우(팔로잉) 취소(db 삭제) 로직 필요 */
    /** 유저와 차단한 상대 팔로우(팔로잉) 수 감소 로직 필요 */

    public BlockedUserDto execute(Long fromUserId, Long toUserId) {
        BlockedUser blockedUser = writeBlockedUserService.create(fromUserId, toUserId);
        BlockedUserDto dto = BlockedUserDto.from(blockedUser);
        return BlockedUserDto.from(blockedUser);
    }
}
