package io.dustin.apps.board.domain.community.posting.repository;

import io.dustin.apps.board.domain.community.posting.model.Posting;
import io.dustin.apps.board.domain.community.posting.repository.custom.CustomPostingRepository;
import io.dustin.apps.common.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingRepository extends BaseRepository<Posting, Long>, CustomPostingRepository {
    Page<Posting> findAll(Pageable pageable);

}
