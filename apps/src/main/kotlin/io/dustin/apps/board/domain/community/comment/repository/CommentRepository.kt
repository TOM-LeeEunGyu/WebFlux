package io.dustin.apps.board.domain.community.comment.repository

import io.dustin.apps.board.domain.community.comment.model.Comment
import io.dustin.apps.board.domain.community.comment.repository.custom.CustomCommentRepository
import io.dustin.apps.common.repository.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CommentRepository : BaseRepository<Comment, Long>, CustomCommentRepository {

}
