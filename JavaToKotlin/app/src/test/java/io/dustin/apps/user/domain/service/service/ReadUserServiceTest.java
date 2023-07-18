package io.dustin.apps.user.domain.service.service;


import io.dustin.apps.common.code.BoardType;
import io.dustin.apps.board.domain.user.model.SiteUser;
import io.dustin.apps.board.domain.user.model.dto.LikeItUserDto;
import io.dustin.apps.board.domain.user.service.ReadUserService;
import io.dustin.apps.board.domain.user.service.WriteUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReadUserServiceTest {

    @Autowired
    private ReadUserService read;

    @Autowired
    private WriteUserService writeUserService;



    @Test
    @DisplayName("아이디로 유저 가져오기")
    public void getUser_테스트() {

        // given
        long id = 1l;

        // when
        SiteUser user = read.getUser(id);

        // then
        assertThat(user.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("좋아요를 누른 유저 목록 가져오기")
    public void likeItUsers_테스트() {

        // given
        Long boardId = 10L;
        BoardType boardType = BoardType.COMMENT;
        Pageable pageable = PageRequest.of(0, 10);

        // when
        List<LikeItUserDto> likeItUsers = read.likeItUsers(boardId, boardType, pageable);

        // then
        assertThat(likeItUsers.size()).isEqualTo(0);


    }


}
