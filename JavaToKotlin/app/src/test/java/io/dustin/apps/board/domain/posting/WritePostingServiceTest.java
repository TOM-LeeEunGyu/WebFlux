package io.dustin.apps.board.domain.posting;

import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.board.domain.community.posting.service.WritePostingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WritePostingServiceTest {

    @Autowired
    WritePostingService writePostingService;



    @Test
    @DisplayName("아이디로 유저 가져오기")
    public void getUser_테스트() {

        // given
        long userID = 1l;
        String subject = "테스트";
        String content = "테스트";


        // when
        Posting data = writePostingService.create(userID,subject,content);
        System.out.println("이거 나오냐" + data);

        // then
//        assertThat(user.getId()).isEqualTo(id);

    }
}
