package io.dustin.apps.board.domain.restriction.posting.service;


import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction;
import io.dustin.apps.board.domain.restriction.posting.repository.PostingRestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.dustin.apps.common.utils.OptionalUtils.getEntity;

@RequiredArgsConstructor
@Service
public class ReadPostingRestrictionService {

    private final PostingRestrictionRepository postingRestrictionRepository;

    public PostingRestriction getPostingRestrictionUser(Long fromUserId, Long toUserId, Long postingId) {
        return getEntity(postingRestrictionRepository.findByFromUserIdAndToUserIdAndPostingId(fromUserId, toUserId, postingId), PostingRestriction.class, "data not found");
    }

}
