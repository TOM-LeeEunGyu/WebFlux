package io.dustin.apps.board.domain.community.posting.model

import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.model.BaseEntity
import org.hibernate.annotations.DynamicUpdate
import jakarta.persistence.*
@Entity
@DynamicUpdate
class Posting(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 200)
    var subject: String,

    @Column(columnDefinition = "TEXT")
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    var isDeleted: YesOrNo = YesOrNo.N,

    @Column(columnDefinition = "bigint default 0")
    var likeCount: Long,

    @Column(columnDefinition = "bigint default 0")
    var clickCount: Long,

    @Column(columnDefinition = "bigint default 0")
    var commentCount: Long,

    var userId: Long

) : BaseEntity() {

    fun updateSubject(subject: String) {
        this.subject = subject
    }

    fun updateContent(content: String) {
        this.content = content
    }

    fun delete() {
        this.isDeleted = YesOrNo.Y
    }

    fun setLikeCount(likeCount: Long) {
        this.likeCount = likeCount
    }

    fun setCommentCount(commentCount: Long) {
        this.commentCount = commentCount
    }

    fun setClickCount(clickCount: Long) {
        this.clickCount = clickCount
    }

}
