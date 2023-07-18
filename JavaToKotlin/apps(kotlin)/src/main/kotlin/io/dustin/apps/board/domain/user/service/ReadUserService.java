package io.dustin.apps.board.domain.user.service;

import io.dustin.apps.common.code.BoardType;
import io.dustin.apps.board.domain.user.model.SiteUser;
import io.dustin.apps.board.domain.user.model.dto.LikeItUserDto;
import io.dustin.apps.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.dustin.apps.common.utils.OptionalUtils.getEntity;

@RequiredArgsConstructor // 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
@Service
public class ReadUserService {

    private final UserRepository userRepository;

    public SiteUser getUser(Long userId) {
        return getEntity(userRepository.findById(userId), SiteUser.class, "site user not found");
    }

    public List<LikeItUserDto> likeItUsers(Long boardId, BoardType boardType, Pageable pageable) {
        return userRepository.findAllLikeItUser(boardId, boardType, pageable);
    }


}
