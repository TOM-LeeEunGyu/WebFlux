package io.dustin.apps.board.domain.bookmark.service;

import io.dustin.apps.board.domain.bookmark.model.Bookmark;
import io.dustin.apps.board.domain.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.dustin.apps.common.utils.OptionalUtils.getEntity;

@RequiredArgsConstructor
@Service
public class ReadBookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Transactional(readOnly = true)
    public Bookmark getBookmark(Long userId, Long boardId) { return getEntity(this.bookmarkRepository.findByUserIdAndBoardId(userId, boardId), Bookmark.class, "bookmark not found"); }
}
