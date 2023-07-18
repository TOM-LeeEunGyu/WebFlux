package io.dustin.apps.board.api.like;

import io.dustin.apps.board.api.usecase.like.DeleteLikeUseCase;
import io.dustin.apps.board.api.usecase.like.WriteLikeUseCase;
import io.dustin.apps.board.domain.like.model.LikeCountService;
import io.dustin.apps.board.domain.like.model.dto.LikeDto;
import io.dustin.apps.board.domain.like.model.dto.LikeItCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LikeController {

    private final WriteLikeUseCase writeLikeUseCase;
    private final DeleteLikeUseCase deleteLikeUsecase;

    @PostMapping("/like")
    public LikeDto like(@RequestBody LikeItCommand command) {
        System.out.println("좋아요 저장했습니다.");
        LikeCountService lcs = command.getBoardType().getBean();
        lcs.likeCount(command.getBoardId());
        return writeLikeUseCase.execute(command.getUserId(), command.getBoardId(), command.getBoardType());
    }

    @PatchMapping("/unlike")
    public LikeDto unlike(@RequestBody LikeItCommand command) {
        System.out.println("좋아요 삭제했습니다.");
        LikeCountService lcs = command.getBoardType().getBean();
        lcs.likeUnCount(command.getBoardId());
        return deleteLikeUsecase.execute(command.getUserId(), command.getBoardId(), command.getBoardType());
    }

}
