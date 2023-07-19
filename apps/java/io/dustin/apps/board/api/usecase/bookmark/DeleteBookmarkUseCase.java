package io.dustin.apps.board.api.usecase.bookmark;

import io.dustin.apps.board.domain.bookmark.model.Bookmark;
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto;
import io.dustin.apps.board.domain.bookmark.service.ReadBookmarkService;
import io.dustin.apps.board.domain.bookmark.service.WriteBookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class DeleteBookmarkUseCase {

    private final WriteBookmarkService writeBookmarkService;
    private final ReadBookmarkService readBookmarkService;

    public BookMarkDto execute(Long userId, Long boardId) {
        Bookmark bookmark = readBookmarkService.getBookmark(userId, boardId);
        if(!bookmark.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        writeBookmarkService.delete(userId, boardId);
        BookMarkDto dto = BookMarkDto.from(bookmark);
        return BookMarkDto.from(bookmark);
    }
}
