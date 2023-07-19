package io.dustin.apps.board.domain.restriction.user.service;

import io.dustin.apps.board.domain.restriction.user.model.UserRestriction;
import io.dustin.apps.board.domain.restriction.user.repository.UserRestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WriteUserRestrictionService {

    private final UserRestrictionRepository userRestrictionRepository;

    public UserRestriction create(Long fromUserId, Long toUserId){
        UserRestriction userRestriction = UserRestriction.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .build();
        this.userRestrictionRepository.save(userRestriction);
        return userRestriction;
    }

    @Transactional
    public void delete(Long fromUserId, Long toUserId) { this.userRestrictionRepository.deleteByFromUserIdAndToUserId(fromUserId, toUserId);}


}
