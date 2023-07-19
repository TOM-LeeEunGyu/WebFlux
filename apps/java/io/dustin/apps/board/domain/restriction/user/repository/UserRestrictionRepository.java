package io.dustin.apps.board.domain.restriction.user.repository;

import io.dustin.apps.board.domain.restriction.user.model.UserRestriction;
import io.dustin.apps.common.repository.BaseRepository;

public interface UserRestrictionRepository extends BaseRepository<UserRestriction, Long> {

    void deleteByFromUserIdAndToUserId(Long fromUserId, Long toUserId);


}
