package io.dustin.apps.board.domain.user.repository;

import io.dustin.apps.board.domain.user.repository.custom.CustomUserRepository;
import io.dustin.apps.common.repository.BaseRepository;
import io.dustin.apps.board.domain.user.model.SiteUser;

import java.util.Optional;

public interface UserRepository extends BaseRepository<SiteUser, Long>, CustomUserRepository {
    Optional<SiteUser> findByNickName(String username);

    Optional<SiteUser> findById(Long userId);

}
