package io.dustin.apps.board.api.usecase.bookmark;

import io.dustin.apps.board.domain.bookmark.model.Bookmark;
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto;
import io.dustin.apps.board.domain.bookmark.service.WriteBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteBookmarkUseCase {

    private final WriteBookmarkService writeBookmarkService;

    public BookMarkDto execute(Long userId, Long boardId) {
        Bookmark bookmark = writeBookmarkService.create(userId, boardId);
        BookMarkDto dto = BookMarkDto.from(bookmark);
        return BookMarkDto.from(bookmark);
    }


}
