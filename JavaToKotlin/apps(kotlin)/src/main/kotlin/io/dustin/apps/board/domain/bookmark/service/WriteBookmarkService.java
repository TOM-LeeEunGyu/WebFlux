package io.dustin.apps.board.domain.bookmark.service;

import io.dustin.apps.board.domain.bookmark.model.Bookmark;
import io.dustin.apps.board.domain.bookmark.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WriteBookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public Bookmark create(Long userId, Long boardId){
        Bookmark bookmark = Bookmark.builder()
                .userId(userId)
                .boardId(boardId)
                .build();
        this.bookmarkRepository.save(bookmark);
        return bookmark;
    }

    @Transactional
    public void delete(Long userId, Long boardId){
        this.bookmarkRepository.deleteByUserIdAndBoardId(userId, boardId);
    }


}
