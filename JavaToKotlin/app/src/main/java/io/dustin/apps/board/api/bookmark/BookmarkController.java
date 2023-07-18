package io.dustin.apps.board.api.bookmark;

import io.dustin.apps.board.api.usecase.bookmark.DeleteBookmarkUseCase;
import io.dustin.apps.board.api.usecase.bookmark.WriteBookmarkUseCase;
import io.dustin.apps.board.domain.bookmark.model.dto.BookMarkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookmarkController {

    private final WriteBookmarkUseCase writeBookmarkUseCase;
    private final DeleteBookmarkUseCase deleteBookmarkUseCase;

    @PostMapping("/bookmarks/add")
    public BookMarkDto add(@RequestBody BookMarkDto bookMarkDto) {
        System.out.println("북마크 저장했습니다.");
        return writeBookmarkUseCase.execute(bookMarkDto.userId(), bookMarkDto.boardId());
    }

    @DeleteMapping("/bookmarks/cancel")
    public BookMarkDto cancel(@RequestBody BookMarkDto bookMarkDto) {
        System.out.println("북마크 삭제했습니다.");
        return deleteBookmarkUseCase.execute(bookMarkDto.userId(), bookMarkDto.boardId());
    }

}
