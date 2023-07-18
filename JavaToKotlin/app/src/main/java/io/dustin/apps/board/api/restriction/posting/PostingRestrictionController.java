package io.dustin.apps.board.api.restriction.posting;

import io.dustin.apps.board.api.usecase.restriction.posting.DeletePostingRestrictionUseCase;
import io.dustin.apps.board.api.usecase.restriction.posting.WritePostingRestrictionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostingRestrictionController {

    private final WritePostingRestrictionUseCase writePostingRestrictionUseCase;
    private final DeletePostingRestrictionUseCase deletePostingRestrictionUseCase;

    //@PostMapping("restriction/{fromUserId}/")
}
