package io.dustin.apps.board.domain.posting;

import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto;
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootTest
public class ReadPostingServiceTest {

    @Autowired
    ReadPostingService readPostingService;



    @Test
    @DisplayName("포스팅 게시물 리스트 가져오기")
    public void getPostings_테스트() {

        // given
        long userID = 1l;
        int page = 0;
        int realSize = 3;
        int querySize = realSize + 1;
        // when
        // 화면에 보여주는 것은 10개 하지만 디비에서는 11개를 가져온다.
        // /postings?size=10
        List<PostingDto> datas = readPostingService.getPostingList(userID, 28L, querySize);

        List<PostingDto> toClient = new ArrayList<>();
        boolean isLast = false;
        Long lastId = null;
        if(datas.size() <= realSize) {
            isLast = true;
            lastId = null;
            toClient = datas;
        } else {
            isLast = false;
            toClient = datas.subList(0, realSize);
            lastId = toClient.stream()
                             .sorted(Comparator.comparing(PostingDto::id))
                             .findFirst().get().id();
        }
        System.out.println(isLast);
        System.out.println(toClient);
        System.out.println(lastId);





        // then
//        assertThat(user.getId()).isEqualTo(id);

    }
}
