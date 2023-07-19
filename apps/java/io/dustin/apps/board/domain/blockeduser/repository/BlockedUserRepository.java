package io.dustin.apps.board.domain.blockeduser.repository;

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser;
import io.dustin.apps.common.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface BlockedUserRepository extends BaseRepository<BlockedUser, Long> {

    void deleteByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
    Optional<BlockedUser> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
    List<BlockedUser> findByFromUserId(Long fromUserId);
    List<BlockedUser> findByToUserId(Long toUserId);


}
