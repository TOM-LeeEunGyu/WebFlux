package io.dustin.apps.board.domain.restriction.posting.service;

import io.dustin.apps.board.domain.restriction.posting.model.PostingRestriction;
import io.dustin.apps.board.domain.restriction.posting.repository.PostingRestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WritePostingRestrictionService {

    private final PostingRestrictionRepository postingRestrictionRepository;

    public PostingRestriction create(Long fromUserId, Long toUserId, Long postingId){
        PostingRestriction postingRestriction = PostingRestriction.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .postingId(postingId)
                .build();
        this.postingRestrictionRepository.save(postingRestriction);
        return postingRestriction;
    }

    public void delete(Long fromUserId, Long toUserId, Long postingId) { postingRestrictionRepository.deleteByFromUserIdAndToUserIdAndPostingId(fromUserId, toUserId, postingId); }
}
