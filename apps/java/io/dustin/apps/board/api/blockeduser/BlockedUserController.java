package io.dustin.apps.board.api.blockeduser;

import io.dustin.apps.board.api.usecase.blockeduser.DeleteBlockedUserUseCase;
import io.dustin.apps.board.api.usecase.blockeduser.WriteBlockedUserUseCase;
import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlockedUserController {

    private final WriteBlockedUserUseCase writeBlockedUserUseCase;
    private final DeleteBlockedUserUseCase deleteBlockedUserUseCase;

    @PostMapping("/block-user/{fromUserId}/block/{toUserId}")
    public BlockedUserDto block(@PathVariable("fromUserId") Long fromUserId,
                              @PathVariable("toUserId") Long toUserId
                              ) {
        return writeBlockedUserUseCase.execute(fromUserId, toUserId);
    }

    @DeleteMapping("/block-user/{fromUserId}/unblock/{toUserId}")
    public BlockedUserDto unblock(@PathVariable("fromUserId") Long fromUserId,
                              @PathVariable("toUserId") Long toUserId
    ) {
        return deleteBlockedUserUseCase.execute(fromUserId, toUserId);
    }

    /**
     * 차단 리스트 가져오기(무한스크롤, querydsl)
     */
}
