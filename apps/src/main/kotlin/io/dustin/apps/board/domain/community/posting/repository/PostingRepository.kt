package io.dustin.apps.board.domain.community.posting.repository

import io.dustin.apps.board.domain.community.posting.model.Posting
import io.dustin.apps.board.domain.community.posting.repository.custom.CustomPostingRepository
import io.dustin.apps.common.repository.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostingRepository : BaseRepository<Posting, Long>, CustomPostingRepository {
}
