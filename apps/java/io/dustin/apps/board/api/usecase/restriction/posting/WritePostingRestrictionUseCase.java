package io.dustin.apps.board.api.usecase.restriction.posting;

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction;
import io.dustin.apps.board.domain.restriction.posting.model.dto.PostingRestrictionDto;
import io.dustin.apps.board.domain.restriction.posting.service.WritePostingRestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritePostingRestrictionUseCase {

    private final WritePostingRestrictionService writePostingRestrictionService;

    public PostingRestrictionDto execute(Long fromUserId, Long toUserId, Long postingId) {
        PostingRestriction postingRestriction = writePostingRestrictionService.create(fromUserId, toUserId, postingId);
        PostingRestrictionDto dto = PostingRestrictionDto.from(postingRestriction);
        return PostingRestrictionDto.from(postingRestriction);
    }



}
