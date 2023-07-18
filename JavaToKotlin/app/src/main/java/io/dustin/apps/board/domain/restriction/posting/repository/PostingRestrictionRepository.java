package io.dustin.apps.board.domain.restriction.posting.repository;

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction;
import io.dustin.apps.common.repository.BaseRepository;

import java.util.Optional;

public interface PostingRestrictionRepository extends BaseRepository<PostingRestriction, Long> {

    void deleteByFromUserIdAndToUserIdAndPostingId(Long fromUserId, Long toUserId, Long postingId);
    Optional<PostingRestriction> findByFromUserIdAndToUserIdAndPostingId(Long fromUserId, Long toUserId, Long postingId);

}
