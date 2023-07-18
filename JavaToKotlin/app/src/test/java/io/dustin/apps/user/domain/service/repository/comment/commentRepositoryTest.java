package io.dustin.apps.user.domain.service.repository.comment;


import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.board.domain.community.comment.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class commentRepositoryTest {

    @Autowired
    private CommentRepository repo;

    @Test
    @DisplayName("comment dto 가져오기")
    public void commentByPosting_테스트() {

        // given
        long longinId = 1L;
        long postingId = 1l;
        int size = 10;
        Long nextId = null;

        List<CommentDto> dtos = repo.commentListByPosting(longinId, postingId, size, null);
        System.out.println(dtos.get(0).posting().getContent());
        // then
        //assertThat(dtos.get(0).posting().getContent()).isEqualTo(id);

    }

}
