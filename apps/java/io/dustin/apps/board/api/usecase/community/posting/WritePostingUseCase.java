package io.dustin.apps.board.api.usecase.community.posting;

import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto;
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService;
import io.dustin.apps.board.domain.community.posting.service.WritePostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WritePostingUseCase {

    private final ReadPostingService readPostingService;
    private final WritePostingService writePostingService;

    @Transactional
    public PostingDto execute(Long userId, String subject, String content) {
        Posting posting = writePostingService.create(userId, subject, content);
        System.out.println("여기는 유즈케이스의 유즈케이스" + posting.toString());
        PostingDto dto = PostingDto.from(posting);
        return PostingDto.from(posting);
    }
}
