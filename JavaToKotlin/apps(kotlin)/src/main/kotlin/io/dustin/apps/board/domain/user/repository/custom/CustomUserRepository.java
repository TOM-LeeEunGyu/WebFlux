package io.dustin.apps.board.domain.user.repository.custom;

import io.dustin.apps.common.code.BoardType;
import io.dustin.apps.board.domain.user.model.dto.LikeItUserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomUserRepository {

    List<LikeItUserDto> findAllLikeItUser(Long boardId, BoardType boardType, Pageable pageable);

    /**
     * 무한스크롤
     */
    List<LikeItUserDto> findAllLikeItUserNoOffSet(Long boardId, BoardType boardType, Pageable pageable);

    /**
     * 로그인 유저의 팔로잉 리스트 보여주는 로직
     * */
//    List<LikeItUserDto> findAllFollowingUser(Long followerID, Long followingId);
//    List<LikeItUserDto> findAllFollowingUserNoOffSet(Long followerID, Long followingId);


    /**
     * 로그인 유저의 팔로워 리스트 보여주는 로직
     * */
//    List<LikeItUserDto> findAllFollowerUser(Long followerID, Long followingId);
//
//    List<LikeItUserDto> findAllFollowerUserNoOffSet(Long followerID, Long followingId);

}
