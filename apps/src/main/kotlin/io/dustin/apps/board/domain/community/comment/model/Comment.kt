package io.dustin.apps.board.domain.community.comment.model

import io.dustin.apps.common.code.YesOrNo
import io.dustin.apps.common.model.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
class Comment (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(columnDefinition = "TEXT")
    var content: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted")
    var isDeleted: YesOrNo = YesOrNo.N,

    @Column(columnDefinition = "bigint default 0")
    var likeCount: Long,

    @Column(columnDefinition = "bigint default 0")
    var clickCount: Long,

    val userId: Long,

    val replyId: Long?,

    val postingId: Long,

    ) : BaseEntity() {

    fun updateContent(content: String) {
        this.content = content
    }

    fun delete() {
        this.isDeleted = YesOrNo.Y
    }

    fun setLikeCount(likeCount: Long) {
        this.likeCount = likeCount
    }

}