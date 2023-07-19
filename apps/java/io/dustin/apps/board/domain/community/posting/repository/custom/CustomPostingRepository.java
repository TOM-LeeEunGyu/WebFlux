package io.dustin.apps.board.domain.community.posting.repository.custom;

import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingListDto;

import java.util.List;

public interface CustomPostingRepository {

    PostingDto getPosting(long userId, long postingId);

    List<PostingListDto> getPostingList(long userId, Long nextId, int size);

    PostingDto getMyPosting(long userId, long postingId);

    List<PostingListDto> getMyPostingList(long userId, Long nextId, int size);

}
